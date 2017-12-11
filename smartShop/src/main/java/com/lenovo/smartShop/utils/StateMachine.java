package com.lenovo.smartShop.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.smartShop.activity.ShopListActivity;
import com.lenovo.smartShop.adapter.ListViewAdapter;
import com.lenovo.smartShop.adapter.SearchAdapter;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.bean.AppListBean;
import com.lenovo.smartShop.view.DownLoadButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by linsen3 on 2017/9/15.
 */

public class StateMachine {

    private static final String TAG = "SC-StateMachine";
    private static StateMachine mInstace;
    private int currentState = 0;
    public static final int defaultPercent = 0;
    private HashMap<String, Integer> hashMap;
    private HashMap<String, Integer> percentMap;
    private int initState = DownLoadButton.STATE_NORMAL;
    private Context mContext;

    private StateMachine(){
        hashMap = new HashMap<>();
        percentMap = new HashMap<>();
    }

    public static StateMachine getInstance(){
        if(mInstace == null){
            mInstace = new StateMachine();
        }
        return mInstace;
    }

    public void initState(final ArrayList<AppListBean.DataBean.DatalistBean> mListAll, final Context context){
        mContext = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (AppListBean.DataBean.DatalistBean datalistBean : mListAll){
                    String appPackageName = datalistBean.getPackageName();
                    int sizeL = datalistBean.getSize();
                    String detailInfoUrl = HttpUtils.commDetailUrl + "pn=" + datalistBean.getPackageName() + "&vc=" + datalistBean.getVersioncode();
                    //getApkServerMd5Str(detailInfoUrl);
                    Log.d(TAG, "appPackageName = " + appPackageName);
                    if(SCPackageManager.isAppInstalled(context, appPackageName)){
                        Log.d(TAG, "Complete");
                        setDownLoadState(appPackageName, DownLoadButton.STATE_COMPLETE);
                    }else {
                        getApkServerMd5Str(mContext, detailInfoUrl, appPackageName, sizeL, true);
                    }
                }

            }
        }).start();

    }

    private String apkMd5;
    public void getApkServerMd5Str(Context context, String detailInfo, final String appPackageName, final int sizeL, final boolean firstLoad){
        mContext = context;
        OkHttpClientUtil.getInstance()._getAsyn(detailInfo, new OkHttpClientUtil.ResultCallback<AppDetailInfoBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDetailInfoBean response) {
                String packName = response.getData().getAppInfo().getPackageName();
                apkMd5 = response.getData().getAppInfo().getApkmd5();
                Log.d(TAG, "packName = " + packName + " /apkMd5 = " + apkMd5);
                /*int fileState = SCPackageManager.queryAppInstallPackage(appPackageName, sizeL, apkMd5);
                if(fileState == SCPackageManager.FILE_EXIST){
                    if(firstLoad){
                        setDownLoadState(appPackageName, DownLoadButton.STATE_INSTALL);
                    }
                } else if(fileState == SCPackageManager.BAD_FILE){
                    Log.d(TAG, "Bad File");
                    setDownLoadState(appPackageName, DownLoadButton.STATE_NORMAL);
                    Toast.makeText(mContext, "文件被损坏,安装失败,请重新下载", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "Normal");
                    if(getDownLoadState(appPackageName) != DownLoadButton.STATE_COMPLETE){
                        setDownLoadState(appPackageName, DownLoadButton.STATE_NORMAL);
                    }
                }
                refreshAdapter();*/


                //modify 11-14
                SCPackageManager.queryAppInstallPackage(appPackageName, sizeL, apkMd5, new SCPackageManager.QueryInstallPackage() {
                    @Override
                    public void onResponseState(int state) {
                        int fileState = state;
                        if(fileState == SCPackageManager.FILE_EXIST){
                            if(firstLoad){
                                setDownLoadState(appPackageName, DownLoadButton.STATE_INSTALL);
                            }
                        } else if(fileState == SCPackageManager.BAD_FILE){
                            Log.d(TAG, "Bad File");
                            setDownLoadState(appPackageName, DownLoadButton.STATE_NORMAL);
                            Looper.prepare();
                            Toast.makeText(mContext, "文件被损坏,安装失败,请重新下载", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        } else {
                            Log.d(TAG, "Normal");
                            if(getDownLoadState(appPackageName) != DownLoadButton.STATE_COMPLETE){
                                setDownLoadState(appPackageName, DownLoadButton.STATE_NORMAL);
                            }
                        }
                        refreshAdapter();
                    }
                });
            }

        });
    }

    public void refreshAdapter(){
        if(mContext != null){
            ((Activity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    /*SearchAdapter adapter = (SearchAdapter) ShopListActivity.listView.getAdapter();
                    adapter.notifyDataSetChanged();*/
                    ListViewAdapter adapter = (ListViewAdapter) ShopListActivity.listView.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void setDownLoadState(String packageName, int state){
        this.currentState = state;
        if(hashMap.containsKey(packageName)){
            hashMap.remove(packageName);
        }
        hashMap.put(packageName, currentState);
        //refreshAdapter();
    }

    public int getDownLoadState(String packageName) {
        if(hashMap.containsKey(packageName)){
            return hashMap.get(packageName);
        }
        return initState;
    }

    public boolean isCurrentState(int state){
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            if(entry.getValue().equals(state)){
                return true;
            }
        }
        return false;
    }

    public void setDownloadPercent(String packageName, int percent){
        if(percentMap.containsKey(packageName)){
            percentMap.remove(packageName);
        }
        percentMap.put(packageName, percent);
    }

    public int getDownloadPercent(String packageName){
        Log.d(TAG, "percentMap = " + percentMap);
        if(percentMap.containsKey(packageName)){
            return percentMap.get(packageName);
        }
        return defaultPercent;
    }

    public void releaseState(){
        hashMap.clear();
        percentMap.clear();
    }
}
