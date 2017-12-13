package com.lenovo.smartShop.adapter;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lenovo.smartShop.R;
import com.lenovo.smartShop.activity.ShopListActivity;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.bean.AppDownLoadBean;
import com.lenovo.smartShop.bean.AppListBean;
import com.lenovo.smartShop.model.DownLoadModel;
import com.lenovo.smartShop.utils.DataFormatConvert;
//import com.lenovo.smartShop.utils.DownLoadManager;
import com.lenovo.smartShop.utils.HttpUtils;
import com.lenovo.smartShop.utils.OkHttpClientUtil;
import com.lenovo.smartShop.utils.SCPackageManager;
import com.lenovo.smartShop.utils.StateMachine;
import com.lenovo.smartShop.utils.WifiControl;
import com.lenovo.smartShop.view.DownLoadButton;
import com.yuan.leopardkit.download.model.DownloadInfo;
import com.yuan.leopardkit.download.DownLoadManager;
import com.tmac.filedownloader.download.DownLoadListener;
import com.tmac.filedownloader.download.TaskInfo;
import com.tmac.filedownloader.download.dbcontrol.FileHelper;
import com.tmac.filedownloader.download.dbcontrol.bean.SQLDownLoadInfo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by linsen3 on 17-10-13.
 */

public class ListViewAdapter extends BaseAdapter {

    public static final String TAG = "SC-ListAdapter";
    private Context context;
    private List<AppListBean.DataBean.DatalistBean> data;
    private List<DownLoadModel> modelList;
    private DownloadInfo info;
    private ArrayList<TaskInfo> listdata = new  ArrayList<TaskInfo>();
    private List<String> mDownLoadUrlList = new ArrayList<>();
    private com.tmac.filedownloader.download.DownLoadManager downLoadManager;
    public static final String FILEPATH = Environment.getExternalStorageDirectory() + "/SmartShop/";

    public ListViewAdapter(Context context, List<AppListBean.DataBean.DatalistBean> data, List<DownLoadModel> modelList,
                           com.tmac.filedownloader.download.DownLoadManager downLoadManager,
                           List<String> downLoadUrlList) {
        this.context = context;
        this.data = data;
        this.modelList = modelList;
        this.downLoadManager = downLoadManager;
        mDownLoadUrlList = downLoadUrlList;
        listdata = downLoadManager.getAllTask();
        downLoadManager.setAllTaskListener(new DowloadManagerListener());
        Log.d(TAG, "task size = " + listdata.size());
        for(TaskInfo taskInfo : listdata){
            Log.d(TAG, "task progress = " + taskInfo.getProgress());
            if(taskInfo.getProgress() > 0){
                StateMachine.getInstance().setDownLoadState(taskInfo.getTaskID(), DownLoadButton.STATE_WAITTING);
                StateMachine.getInstance().setDownloadPercent(taskInfo.getTaskID(), taskInfo.getProgress());
            }
        }
    }
    
    public void setTaskInfoList(ArrayList<TaskInfo> listdata){
        this.listdata = listdata;
        this.notifyDataSetInvalidated();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setModelList(ArrayList<DownLoadModel> modelList){
        this.modelList = modelList;
        for(int i = 0; i < modelList.size(); i++){
            String packageName = modelList.get(i).getInfo().getFileName();
            int state = modelList.get(i).getInfo().getState();
            if(state == DownLoadManager.STATE_PAUSE){
                StateMachine.getInstance().setDownLoadState(packageName, DownLoadButton.STATE_WAITTING);
            }
        }
        notifyDataSetChanged();
        Log.d(TAG, "modelList = " + modelList.size());
    }

    private int downLoadState;
    private ViewHolder holder = null;
    private String downLoadUrl = null;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.tv_item = convertView.findViewById(R.id.tv_item);
            holder.tv_item_download = convertView.findViewById(R.id.tv_item_download);
            holder.tv_item_size = convertView.findViewById(R.id.tv_item_size);
            holder.tv_item_desc = convertView.findViewById(R.id.tv_item_desc);
            holder.iv_item = convertView.findViewById(R.id.iv_item);
            holder.btn_item = convertView.findViewById(R.id.btn_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_item.setText(data.get(position).getName());
        holder.tv_item_download.setText(data.get(position).getDownloadCount());
        holder.tv_item_size.setText(DataFormatConvert.formatData(data.get(position).getSize()));
        holder.tv_item_desc.setText(data.get(position).getTypeName());
        Glide.with(context).load(data.get(position).getIconAddr()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.iv_item);

        final String packageName = data.get(position).getPackageName();
        final String mUrl = HttpUtils.commDetailUrl + "pn=" + packageName + "&vc=" + data.get(position).getVersioncode();
        downLoadState = StateMachine.getInstance().getDownLoadState(packageName);
        holder.btn_item.setVisibility(View.VISIBLE);
        //Log.d(TAG, "mUrl = " + mUrl);
        Log.d(TAG, "downLoadState = " + downLoadState);
        // 按钮状态
        switch (downLoadState) {
            case DownLoadButton.STATE_NORMAL:
                //Log.d(TAG, "Normal");
                holder.btn_item.setState(packageName, DownLoadButton.STATE_NORMAL);
                break;
            case DownLoadButton.STATE_DOWNLOADING:
                //Log.d(TAG, "Downloading");
                holder.btn_item.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
                break;
            case DownLoadButton.STATE_INSTALL:
                //Log.d(TAG, "Install");
                holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALL);
                break;
            case DownLoadButton.STATE_COMPLETE:
                //Log.d(TAG, "Complete");
                holder.btn_item.setState(packageName, DownLoadButton.STATE_COMPLETE);
                break;
            case DownLoadButton.STATE_WAITTING:
                holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
                break;
            case DownLoadButton.STATE_INSTALLING:
                holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALLING);
                break;
        }

        holder.btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(listdata.size() > position){
                    downLoadState = StateMachine.getInstance().getDownLoadState(packageName);
                    Log.d(TAG, "position = " + position + "\n" +"packageName = " + packageName);
                    if(downLoadState == DownLoadButton.STATE_NORMAL){
                        if(WifiControl.wifiIsConnected()){
                            Log.d(TAG, "============== ADD_TASK ");
                            getDownLoadUrl(mUrl, packageName);
                        }else {
                            Toast.makeText(context, "网络未连接,请检查", Toast.LENGTH_SHORT).show();
                        }
                    } else if (downLoadState == DownLoadButton.STATE_COMPLETE) {
                        Log.d(TAG, "open application");
                        SCPackageManager.startActivityByPn(packageName);
                    } else if (downLoadState == DownLoadButton.STATE_INSTALL) {
                        holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALLING);
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installPacakageByPm(new File(ListViewAdapter.FILEPATH, packageName));
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installApk(new File(FileHelper.getFileDefaultPath(), "/(" + packageName + ")" + packageName));
                        com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).sendInstallMessage(new File(FileHelper.getFileDefaultPath(), "/(" + packageName + ")" + packageName));
                    } else if (downLoadState == DownLoadButton.STATE_WAITTING){
                        if(WifiControl.wifiIsConnected()){
                            // 继续下载
                            Log.d(TAG, "restart taskID = " + packageName);
                            //listdata.get(position).setOnDownloading(true);
                            downLoadManager.startTask(packageName);
                            holder.btn_item.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
                        }else {
                            Toast.makeText(context, "网络异常,请检查", Toast.LENGTH_SHORT).show();
                        }
                    } else if (downLoadState == DownLoadButton.STATE_DOWNLOADING){
                        Log.d(TAG, "stop taskID = " + packageName);
                        //listdata.get(position).setOnDownloading(false);
                        downLoadManager.stopTask(packageName);
                        holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
                    }
                //}
            }
        });



        /*if(modelList != null && modelList.size() > 0){
            info = modelList.get(position).getInfo();
            Log.d(TAG, position + " info state= " + info.getState() + " size = " + modelList.size());
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
                    if(done){
                        holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALLING);
                        renameFile(packageName);
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installApk(new File(ListViewAdapter.FILEPATH, packageName));
                        StateMachine.getInstance().getApkServerMd5Str(context, mUrl, packageName, 0, false);
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installPacakageByPm(new File(ListViewAdapter.FILEPATH, packageName));
                        com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).sendInstallMessage(new File(ListViewAdapter.FILEPATH, packageName));
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installApk(new File(ListViewAdapter.FILEPATH, packageName));
                    }
                }

                @Override
                public void onSucess(String result) {
                    Log.i(TAG, " success : " + result);
                }

                @Override
                public void onFailed(Throwable e, String reason) {
                    // 设置继续按钮，显示原因
                    DownLoadManager.getManager().pauseTask(info);
                    Log.d(TAG, "failed reason = " + reason);
                    //Toast.makeText(context, "网络异常,请检查", Toast.LENGTH_SHORT).show();
                    holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
                    notifyDataSetChanged();
                }
            });

            if(info.getState() == DownLoadManager.STATE_PAUSE){
                holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
                int percent = (int) ((float) info.getProgress() /  info.getFileLength() * 100);
                Log.d(TAG, "percent = " + percent + " breakProgress = " + info.getBreakProgress());
                StateMachine.getInstance().setDownloadPercent(packageName, (int) ((float) info.getProgress() /  info.getFileLength() * 100));
            }
        }



        // 点击事件
        holder.btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click modelList = " + modelList);
                if(modelList != null && modelList.size() > 0){
                    DownloadInfo info = modelList.get(position).getInfo();
                    downLoadState = StateMachine.getInstance().getDownLoadState(info.getFileName());
                    Log.d(TAG, "position = " + position + " name = " + info.getFileName() + "state = " + downLoadState);
                    if (downLoadState == DownLoadButton.STATE_NORMAL) {
                        if(WifiControl.wifiIsConnected()){
                            //开始下载
                            holder.btn_item.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
                            //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).appInfoRequest(mUrl, context, holder.btn_item, position, false);
                            DownLoadManager.getManager().restartTask(info);
                        }else {
                            Toast.makeText(context, "网络未连接,请检查", Toast.LENGTH_SHORT).show();
                        }
                    } else if (downLoadState == DownLoadButton.STATE_COMPLETE) {
                        Log.d(TAG, "open application");
                        SCPackageManager.startActivityByPn(packageName);
                    } else if (downLoadState == DownLoadButton.STATE_INSTALL) {
                        holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALLING);
                        //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installApk(new File(SCPackageManager.PATH, packageName));
                        com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installPacakageByPm(new File(ListViewAdapter.FILEPATH, packageName));
                    } else if (downLoadState == DownLoadButton.STATE_WAITTING){
                        if(WifiControl.wifiIsConnected()){
                            // 继续下载
                            DownLoadManager.getManager().resumeTask(info);
                            holder.btn_item.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
                        }else {
                            Toast.makeText(context, "网络异常,请检查", Toast.LENGTH_SHORT).show();
                        }
                    } else if (downLoadState == DownLoadButton.STATE_DOWNLOADING){
                        DownLoadManager.getManager().pauseTask(info);
                        holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
                        notifyDataSetChanged();
                    }
                }
            }
        });*/

        return convertView;
    }

    private class ViewHolder {
        TextView tv_item, tv_item_download, tv_item_size, tv_item_desc;
        ImageView iv_item;
        DownLoadButton btn_item;
    }

    private int currentPencent;
    private int percent;
    private class DowloadManagerListener implements DownLoadListener{

        @Override
        public void onStart(SQLDownLoadInfo sqlDownLoadInfo) {
            Log.d(TAG, "==============onStart " + sqlDownLoadInfo.getTaskID());
            currentPencent = 0;
        }

        @Override
        public void onProgress(SQLDownLoadInfo sqlDownLoadInfo, boolean isSupportBreakpoint) {
            Log.d(TAG, "==============onProcess " + sqlDownLoadInfo.getTaskID());
            //根据监听到的信息查找列表相对应的任务，更新相应任务的进度
            for (TaskInfo taskInfo : listdata) {
                if(taskInfo.getTaskID().equals(sqlDownLoadInfo.getTaskID())){
                    taskInfo.setDownFileSize(sqlDownLoadInfo.getDownloadSize());
                    taskInfo.setFileSize(sqlDownLoadInfo.getFileSize());
                    percent = ((int)(100 * sqlDownLoadInfo.getDownloadSize()/sqlDownLoadInfo.getFileSize()));
                    // 多了这个会导致其他button无法点击,因为一直在刷新
                    //ListViewAdapter.this.notifyDataSetChanged();
                    break;
                }
            }
            int position = getPositionByPackageName(sqlDownLoadInfo.getTaskID());
            ListView listView = ShopListActivity.listView;
            Log.d(TAG, "============== STATE_DOWNLOADING " + percent + " " + currentPencent + "\n" + listView.getFirstVisiblePosition() + "\n" + listView.getLastVisiblePosition());
            if (percent > (StateMachine.getInstance().getDownloadPercent(sqlDownLoadInfo.getTaskID()) + 4)
                    && position >= listView.getFirstVisiblePosition()
                    && position <= listView.getLastVisiblePosition()) {
                currentPencent = percent;
                int positionInListView = position - listView.getFirstVisiblePosition();
                DownLoadButton btn = listView.getChildAt(positionInListView).findViewById(R.id.btn_item);
                Log.d(TAG, "==============  " + percent);
                btn.setDownLoadProgress(DownLoadButton.STATE_DOWNLOADING, percent, sqlDownLoadInfo.getTaskID());
            }
        }

        @Override
        public void onStop(SQLDownLoadInfo sqlDownLoadInfo, boolean isSupportBreakpoint) {
            Log.d(TAG, "==============onStop " + sqlDownLoadInfo.getTaskID());
        }

        @Override
        public void onError(SQLDownLoadInfo sqlDownLoadInfo) {
            Log.d(TAG, "==============  onError " + sqlDownLoadInfo.getTaskID());
            //根据监听到的信息查找列表相对应的任务，停止该任务
            for(TaskInfo taskInfo : listdata){
                if(taskInfo.getTaskID().equals(sqlDownLoadInfo.getTaskID())){
                    taskInfo.setOnDownloading(false);
                    break;
                }
            }
            String packageName = sqlDownLoadInfo.getTaskID();
            holder.btn_item.setState(packageName, DownLoadButton.STATE_WAITTING);
            ListViewAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onSuccess(SQLDownLoadInfo sqlDownLoadInfo) {
            Log.d(TAG, "==============onSuccess " + sqlDownLoadInfo.getTaskID());
            String packageName = sqlDownLoadInfo.getTaskID();
            holder.btn_item.setState(packageName, DownLoadButton.STATE_INSTALLING);
            //根据监听到的信息查找列表相对应的任务，删除对应的任务
            for(TaskInfo taskInfo : listdata){
                if(taskInfo.getTaskID().equals(sqlDownLoadInfo.getTaskID())){
                    listdata.remove(taskInfo);
                    ListViewAdapter.this.notifyDataSetChanged();
                    break;
                }
            }
            //com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).installApk(new File(FileHelper.getFileDefaultPath(), "/(" + sqlDownLoadInfo.getFileName() + ")" + sqlDownLoadInfo.getFileName()));
            com.lenovo.smartShop.utils.DownLoadManager.getInstance(context).sendInstallMessage(new File(FileHelper.getFileDefaultPath(), "/(" + sqlDownLoadInfo.getFileName() + ")" + sqlDownLoadInfo.getFileName()));
        }
    }

    private int getPositionByPackageName(String packageName){
        if(data != null && data.size() > 0){
            for (int i = 0; i < data.size(); i++){
                if(data.get(i).getPackageName().equals(packageName)){
                    return i;
                }
            }
        }
        return 0;
    }

    private void getDownLoadUrl(String url, final String packageName){
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDetailInfoBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDetailInfoBean response) {
                String downLoadInfo = response.getData().getAppInfo().getAppDownloadAdr();
                getDownLoadInfo(downLoadInfo, packageName);
            }
        });
    }

    private void getDownLoadInfo(String url, final String packageName){
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDownLoadBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDownLoadBean response) {
                String downLoadUrl = response.getData().getDownurl();
                Log.d(TAG, "pkg = " + packageName + " / downLoadUrl = " + downLoadUrl);
                downLoadManager.addTask(packageName, downLoadUrl, packageName);
                holder.btn_item.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
                listdata = downLoadManager.getAllTask();
            }
        });
    }

    public void stopAllTask(){
        if(downLoadManager != null){
            downLoadManager.stopAllTask();
        }
    }

    public void saveAllTaskInfo(){
        if(downLoadManager != null){
            downLoadManager.saveAllTaskInfo();
        }
    }

    private String fileCacheNem = ".cache";
    private void renameFile(String packageName){
        //更新文件
        File file = new File(FILEPATH + packageName +fileCacheNem);
        Log.d("LS-" + TAG, "file = " + file.getName() + " " + file.exists());
        if(file.exists()){
            Log.d(TAG, "rename file : " + packageName);
            file.renameTo(new File(FILEPATH + packageName));
        }
    }
}
