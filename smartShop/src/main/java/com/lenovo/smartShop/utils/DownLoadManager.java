package com.lenovo.smartShop.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ListView;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.activity.ShopListActivity;
import com.lenovo.smartShop.adapter.ListViewAdapter;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.bean.AppDownLoadBean;
import com.lenovo.smartShop.view.DownLoadButton;
import com.yuan.leopardkit.LeopardHttp;
import com.yuan.leopardkit.download.model.DownloadInfo;
import com.yuan.leopardkit.interfaces.IDownloadProgress;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Request;

/**
 * Created by linsen3 on 2017/9/6.
 */

public class DownLoadManager {
    private static final String TAG = "SC-DownLoadManager";
    private static Context mContext;
    private static File file;
    private Handler mHandler;
    private static DownLoadManager mInstance;
    private HandlerThread handlerThread;

    private DownLoadManager(Context context){
        mContext = context;
        handlerThread = new HandlerThread("install-package");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                File file = (File) msg.obj;
                installPacakageByPm(file);
            }
        };
    }

    public static DownLoadManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new DownLoadManager(context);
        }
        return mInstance;
    }

    // 安装APK
    public void installApk(File file) {
        Log.d(TAG, "install file = " + file);
        Intent intent = new Intent();
        // 执行动作
        intent.setAction(Intent.ACTION_VIEW);
        // 给目标应用一个临时授权
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 执行的数据类型
        intent.setDataAndType(getUriForFile(mContext, file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }


    private Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.lenovo.smartShop.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    public void appInfoRequest(String url, final Context context, final DownLoadButton downLoadButton, final int position, final boolean isSearch){
        mContext = context;
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDetailInfoBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDetailInfoBean response) {
                Log.d(TAG, "AppDetailInfoBean = " + response.toString());
                String downLoadUrl = response.getData().getAppInfo().getAppDownloadAdr();
                Log.d(TAG, "downLoadUrl = " + downLoadUrl);
                appDownloadInfoRequest(downLoadUrl, context, downLoadButton, position, isSearch);
            }
        });
    }

    private DownloadInfo info;
    public void appDownloadInfoRequest(String url, Context context, final DownLoadButton downLoadButton, final int position, final boolean isSearch){
        mContext = context;
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDownLoadBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDownLoadBean response) {
                String downloadUrl = response.getData().getDownurl();
                final String packageName = response.getData().getApp_package_name();
                Log.d(TAG, "AppDownLoadBean = " + response.toString() + "\n" + downloadUrl + "\n" + packageName);
                //downLoadApk(mContext, downloadUrl, packageName, downLoadButton, position, isSearch);
                info = new DownloadInfo();
                info.setUrl(downloadUrl);
                info.setFileSavePath(ListViewAdapter.FILEPATH);// 自定义下载路径
                info.setFileName(packageName);
                final int[] tem = {0};
                long result = LeopardHttp.DWONLOAD(info, new IDownloadProgress() {
                    @Override
                    public void onProgress(long key, long progress, long total, boolean done) {
                        Log.i(TAG, " progress : " + progress + "now total :" + total);

                        int percent = (int) ((float) progress / total * 100);

                        ListView listView = ShopListActivity.listView;
                        if (percent > tem[0] && position >= listView.getFirstVisiblePosition() && position <= listView.getLastVisiblePosition()) {
                            tem[0] = percent;
                            int positionInListView = position - listView.getFirstVisiblePosition();
                            DownLoadButton btn = listView.getChildAt(positionInListView).findViewById(R.id.btn_item);
                            btn.setDownLoadProgress(DownLoadButton.STATE_DOWNLOADING, percent, packageName);
                        }
                        if(percent == 100){
                            downLoadButton.setState(packageName, DownLoadButton.STATE_INSTALL);
                            installApk(new File(ListViewAdapter.FILEPATH, packageName));
                        }
                    }

                    @Override
                    public void onSucess(String result) {
                        Log.i(TAG, " success : " + result);
                    }

                    @Override
                    public void onFailed(Throwable e, String reason) {
                        // 设置继续按钮，显示原因
                        Log.d(TAG, "failed reason = " + reason);
                        downLoadButton.setState(packageName, DownLoadButton.STATE_WAITTING);
                        com.yuan.leopardkit.download.DownLoadManager.getManager().pauseTask(info);
                    }
                });
                com.yuan.leopardkit.download.DownLoadManager.getManager().restartTask(info);
            }
        });

    }

    public void downLoadApk(Context context, final String url, final String packageName, final DownLoadButton downLoadButton, int position, boolean isSearch){
        mContext = context;

        OkHttpClientUtil.downloadAsyn(url, packageName, new OkHttpClientUtil.ResultCallback<Object>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.d(TAG, "error : " + e.getMessage().toString());
            }

            @Override
            public void onResponse(Object response) {
                installApk((File) response);
            }
        }, downLoadButton, position, isSearch);
    }


    public File getFileFromServer(String path, String apkName, DownLoadButton downLoadButton) throws Exception {
        // 将server端的apk下载到手机的File中
        // 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            //pd.setMax(conn.getContentLength());
            int length = conn.getContentLength();
            InputStream is = conn.getInputStream();
            file = new File(Environment.getExternalStorageDirectory(), apkName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len = 0;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                // 获取当前下载量
                //pd.setProgress(total);
                int percent = total/(length/100);
                //Log.d(TAG, "total = " + total + " /length = " + length + " /percent = " + percent);
                downLoadButton.setDownLoadProgress(DownLoadButton.STATE_DOWNLOADING, percent, "");
            }
            downLoadButton.setState(apkName, DownLoadButton.STATE_INSTALL);
            fos.close();
            bis.close();
            is.close();
            Log.d(TAG, "file = " + file);
            return file;
        } else {
            return null;
        }
    }

    public void installPacakageByPm(File file){

        String[] args = {"pm", "install", "-r", getUriForFile(mContext, file).getPath()};
        final String[] result = {null};
        final ProcessBuilder processBuilder = new ProcessBuilder(args);
        final Process[] process = {null};
        final InputStream[] errIs = {null};
        final InputStream[] inIs = {null};

        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read=-1;
            process[0] = processBuilder.start();
            errIs[0] = process[0].getErrorStream();
            while((read = errIs[0].read()) != -1){
                baos.write(read);
            }
            baos.write('\n');
            inIs[0] = process[0].getInputStream();
            while((read= inIs[0].read())!=-1){
                baos.write(read);
            }

            byte[] data=baos.toByteArray();

            result[0] = new String(data);

        } catch (IOException e) {
            e.printStackTrace();
            String log = e.toString() + ":" + "file is write ex!";
            Log.e("", log);
        }

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int read=-1;
                    process[0] = processBuilder.start();
                    errIs[0] = process[0].getErrorStream();
                    while((read = errIs[0].read()) != -1){
                        baos.write(read);
                    }
                    baos.write('\n');
                    inIs[0] = process[0].getInputStream();
                    while((read= inIs[0].read())!=-1){
                        baos.write(read);
                    }

                    byte[] data=baos.toByteArray();

                    result[0] = new String(data);

                } catch (IOException e) {
                    e.printStackTrace();
                    String log = e.toString() + ":" + "file is write ex!";
                    Log.e("", log);
                }
            }
        }).start();*/


    }



    public void sendInstallMessage(File file){
        Message msg = new Message();
        msg.obj = file;
        mHandler.sendMessage(msg);
    }

    public void installPackageByReflect(File file){
        Class<?>pmService;
        Class<?> activityTherad;
        Method method;

        try {
            activityTherad = Class.forName("android.app.ActivityThread");
            Class<?> paramTypes[] = getParamTypes(activityTherad, "getPackageManager");
            method = activityTherad.getMethod("getPackageManager", paramTypes);
            Object PackageManagerService = method.invoke(activityTherad);
            pmService = PackageManagerService.getClass();
            Class<?> paramTypes1[] =getParamTypes(pmService, "installPackage");
            method = pmService.getMethod("installPackage", paramTypes1);
            method.invoke(PackageManagerService, (Object) getUriForFile(mContext, file), null, 0, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private Class<?>[] getParamTypes(Class<?> cls, String mName){
        Class<?> cs[] = null;
        Method[] mtd = cls.getMethods();

        for (int i = 0; i < mtd.length; i++){
            if(!mtd[i].getName().equals(mName)){
                continue;
            }
            cs = mtd[i].getParameterTypes();
        }
        return cs;
    }

}

