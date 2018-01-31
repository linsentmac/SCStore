package com.lenovo.smartShop.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.adapter.ListViewAdapter;
import com.lenovo.smartShop.adapter.SearchAdapter;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.bean.AppDownLoadBean;
import com.lenovo.smartShop.bean.AppListBean;
import com.lenovo.smartShop.model.DownLoadModel;
import com.lenovo.smartShop.utils.HttpUtils;
import com.lenovo.smartShop.utils.InstalledReceiver;
import com.lenovo.smartShop.utils.MyApplication;
import com.lenovo.smartShop.utils.OkHttpClientUtil;
import com.lenovo.smartShop.utils.StateMachine;
import com.lenovo.smartShop.utils.StatusBarUtil;
import com.lenovo.smartShop.utils.WifiControl;
import com.lenovo.smartShop.view.DownLoadButton;
import com.yuan.leopardkit.LeopardHttp;
import com.yuan.leopardkit.download.DownLoadManager;
import com.yuan.leopardkit.download.model.DownloadInfo;
import com.tmac.filedownloader.download.DownLoadService;
import com.tmac.filedownloader.download.TaskInfo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class ShopListActivity extends Activity {

    private RelativeLayout serachLayout;
    public static ListView listView;
    private ProgressBar progressBar;
    private ArrayList<AppListBean.DataBean.DatalistBean> mListAll;
    private ArrayList<String> packageList;
    private ArrayList<DownLoadModel> list;
    private ArrayList<DownLoadModel> modelList;
    private ArrayList<String> downLoadUrlList;
    private ArrayList<Integer> pkgSizeList;
    private MyApplication myApplication;
    private ImageView disConn_Img;

    private SearchAdapter searchAdapter;
    private ListViewAdapter listViewAdapter;
    private WifiControl mWifiControl;
    private InstalledReceiver installedReceiver;
    private static final int MSG_NETWORK_RESPONSE = 0;
    private static final long DELAY_TIME = 5 * 1000;
    private final String TAG = "SC-ShopList";
    private boolean firstLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        StatusBarUtil.adjustTranslentWindow(this);
        setContentView(R.layout.activity_shop_list);
        myApplication = (MyApplication) getApplication();
        mWifiControl = new WifiControl(this);
        initViews();
        initEvents();
        StatusBarUtil.setWhiteTranslucent(this);


        /**  因为是单例模式进行构建leopard请求，所以请求前需要先进行域名绑定 **/
        //LeopardHttp.bindServer("http://wxwusy.applinzi.com");
        packageList = new ArrayList<>();
        mListAll = new ArrayList<>();
        list = new ArrayList<>();
        modelList = new ArrayList<>();
        downLoadUrlList = new ArrayList<>();
        pkgSizeList = new ArrayList<>();
        searchAdapter = new SearchAdapter(ShopListActivity.this, mListAll, R.layout.list_item, false);
        getWifiState();
        //下载管理器需要启动一个Service,在刚启动应用的时候需要等Service启动起来后才能获取下载管理器，所以稍微延时获取下载管理器
        handler.sendEmptyMessageDelayed(0, 50);
        /*listViewAdapter = new ListViewAdapter(ShopListActivity.this, mListAll, modelList, manager, downLoadUrlList);
        listView.setAdapter(listViewAdapter);*/
        registerReceiver();
    }

    private void registerReceiver(){
        installedReceiver = new InstalledReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        registerReceiver(installedReceiver, filter);
    }

    private com.tmac.filedownloader.download.DownLoadManager manager;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /*获取下载管理器*/
            manager = DownLoadService.getDownLoadManager();
            if(manager != null){
                /*设置用户ID，客户端切换用户时可以显示相应用户的下载任务*/
                manager.changeUser("smartcast");
                /*断点续传需要服务器的支持，设置该项时要先确保服务器支持断点续传功能*/
                manager.setSupportBreakpoint(true);
                listViewAdapter = new ListViewAdapter(ShopListActivity.this, mListAll, modelList, manager, downLoadUrlList);
                listView.setAdapter(listViewAdapter);
            }else {
                sendEmptyMessageDelayed(0, 50);
            }

            //adapter = new ListAdapter(MainActivity.this,manager);
            //listview.setAdapter(adapter);
            //userbutton.setText("用户 : " + manager.getUserID());
        }
    };

    private ArrayList<TaskInfo> listdata;
    private void initDownLoadTask(ArrayList<AppListBean.DataBean.DatalistBean> mListAll){
        listdata = new ArrayList<>();
        int taskSize = manager.getAllTask().size();
        if(taskSize == 0){
            for(AppListBean.DataBean.DatalistBean datalistBean : mListAll){
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setFileName(datalistBean.getPackageName());
                taskInfo.setTaskID(datalistBean.getPackageName());
                listdata.add(taskInfo);
            }
        }else if (taskSize < mListAll.size()){
            for(int index = taskSize; index < mListAll.size(); index++){
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setFileName(mListAll.get(index).getPackageName());
                taskInfo.setTaskID(mListAll.get(index).getPackageName());
                listdata.add(taskInfo);
            }
        }else {
            Log.d(TAG, "TaskInfo size = " + taskSize + "\n" + "mListAll size = " + mListAll.size());
        }

        listViewAdapter.setTaskInfoList(listdata);
    }

    private void initViews() {
        serachLayout = (RelativeLayout) findViewById(R.id.search_relaLay);
        listView = (ListView) findViewById(R.id.listView);
        disConn_Img = (ImageView) findViewById(R.id.disconnect_Img);
        progressBar = findViewById(R.id.load_progress);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        searchAdapter.notifyDataSetChanged();

        //initData();
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "只有允许使用该设备的位置信息才能搜索到WiFi", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void getWifiState(){
        ConnectivityManager connectMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if ( wifiNetInfo.isConnected() && wifiNetInfo.getState() == NetworkInfo.State.CONNECTED) {// unconnect network
            listView.setVisibility(View.VISIBLE);
            disConn_Img.setVisibility(View.GONE);
            if(mListAll != null && mListAll.size() == 0){
                progressBar.setVisibility(View.VISIBLE);
            }
            dataRequest();
            mHandler.sendEmptyMessageDelayed(MSG_NETWORK_RESPONSE, DELAY_TIME);
            Log.d(TAG, "isConnected");
        } else {
            progressBar.setVisibility(View.GONE);
            disConn_Img.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
            //dataRequest();
            Log.d(TAG, "isDisConnected");
        }
    }

    private void dataRequest(){
        //progressBar.setVisibility(View.VISIBLE);
        /*if(!StateMachine.getInstance().isCurrentState(DownLoadButton.STATE_DOWNLOADING)){
        }else {
            progressBar.setVisibility(View.GONE);
            removeMessage();
        }*/
        Log.d(TAG, "dataRequest");
        OkHttpClientUtil.getInstance()._getAsyn(HttpUtils.listUrl, new OkHttpClientUtil.ResultCallback<AppListBean>(){

            @Override
            public void onError(Request request, Exception e) {
                Log.d(TAG, "error = " + e.toString());
                if(mListAll != null && mListAll.size() == 0){
                    progressBar.setVisibility(View.GONE);
                    disConn_Img.setVisibility(View.VISIBLE);
                }else {
                    removeMessage();
                }
            }

            @Override
            public void onResponse(AppListBean response) {
                Log.d(TAG, "response = " + response.toString());
                progressBar.setVisibility(View.GONE);
                disConn_Img.setVisibility(View.GONE);
                removeMessage();
                if(mListAll.size() != 0){
                    mListAll.clear();
                    packageList.clear();
                    pkgSizeList.clear();
                }
                for(int i = 0; i < response.getData().getAllcount(); i++){
                    mListAll.add(response.getData().getDatalist().get(i));
                    packageList.add(response.getData().getDatalist().get(i).getPackageName());
                    pkgSizeList.add(response.getData().getDatalist().get(i).getSize());
                }
                myApplication.setDataList(mListAll);
                //searchAdapter.notifyDataSetChanged();
                //initData();
                //initDownLoadTask(mListAll);
                //List<DownloadInfo> downloadInfoList = DownLoadManager.getManager().getDownloadList(ShopListActivity.this);
                refreshAdapter();
                //listViewAdapter.notifyDataSetChanged();
                Log.d(TAG, "firstLoad = " + firstLoad);
                if(firstLoad){
                    StateMachine.getInstance().initState(mListAll, ShopListActivity.this);
                    firstLoad = false;
                }
            }
        });

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == MSG_NETWORK_RESPONSE){
                Log.d(TAG, "MSG_NETWORK_RESPONSE");
                progressBar.setVisibility(View.GONE);
                disConn_Img.setVisibility(View.VISIBLE);
            }
        }
    };

    private void removeMessage(){
        if(mHandler.hasMessages(MSG_NETWORK_RESPONSE)){
            mHandler.removeMessages(MSG_NETWORK_RESPONSE);
        }
    }


    private List<DownloadInfo> downloadInfoList;
    private void initData(){
        releaseList();
        downloadInfoList = DownLoadManager.getManager().getDownloadList(ShopListActivity.this);
        Log.d(TAG, "downloadInfoList" + downloadInfoList.toString());
        if(downloadInfoList.size() == 0){
            for(AppListBean.DataBean.DatalistBean datalistBean : mListAll){
                final String murl = HttpUtils.commDetailUrl + "pn=" + datalistBean.getPackageName() + "&vc=" + datalistBean.getVersioncode();
                getDownLoadUrl(murl, datalistBean.getPackageName(), false);
            }
        }else if(downloadInfoList.size() < mListAll.size()){
            for(AppListBean.DataBean.DatalistBean datalistBean : mListAll){
                final String murl = HttpUtils.commDetailUrl + "pn=" + datalistBean.getPackageName() + "&vc=" + datalistBean.getVersioncode();
                getDownLoadUrl(murl, datalistBean.getPackageName(), true);
            }
        }else {
            for(String packageName : packageList){
                for(DownloadInfo info : downloadInfoList){
                    if(info.getFileName().equals(packageName)){
                        DownLoadModel model = new DownLoadModel();
                        model.setInfo(info);
                        modelList.add(model);
                        break;
                    }
                }
            }
            Log.d(TAG, "model size = " + modelList.size());
            for (int i = 0; i < modelList.size(); i++){
                String packageName = modelList.get(i).getInfo().getFileName();
                Log.d(TAG, "model name = " + packageName);
                if(modelList.get(i).getInfo().getState() == DownLoadManager.STATE_PAUSE){
                    StateMachine.getInstance().setDownLoadState(packageName, DownLoadButton.STATE_WAITTING);
                }
            }
        }
    }

    private void releaseList(){
        if(modelList.size() != 0){
            modelList.clear();
        }
        if(list.size() != 0){
            list.clear();
        }
        if(convertList.size() != 0){
            convertList.clear();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initEvents() {
        serachLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myApplication.setDataList(mListAll);
                Intent intent = new Intent(ShopListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                searchAdapter.notifyDataSetChanged();
                Intent intent = new Intent(ShopListActivity.this, AppDetailInfoActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        disConn_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWifiState();
            }
        });
    }

    private void refreshAdapter(){
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        if(listViewAdapter != null){
            Log.d(TAG, "saveAllTaskInfo");
            listViewAdapter.saveAllTaskInfo();
        }
    }

    @Override
    protected void onDestroy() {
        // 引用对象销毁时，释放下载资源（当然建议在后台服务跑下载进程）
        DownLoadManager.getManager().release();
        super.onDestroy();
        Log.d(TAG, "onDestory");
        StateMachine.getInstance().releaseState();
        if(listViewAdapter != null){
            listViewAdapter.stopAllTask();
        }
        unregisterReceiver(installedReceiver);
    }


    /*@Override
    public void onBackPressed() {
        *//*if(StateMachine.getInstance().isCurrentState(DownLoadButton.STATE_DOWNLOADING)){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
            intent.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(intent);
        }else {
            Log.d(TAG, "onBackPressed");
            super.onBackPressed();
        }*//*
        *//*Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
        intent.addCategory(Intent.CATEGORY_HOME);
        this.startActivity(intent);*//*
        super.onBackPressed();
    }*/

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        String message;
        int count = listViewAdapter.getDownloadCount();
        if(count == 0){
            message = "确定要退出吗?";
        }else {
            message = "当前还有" + count + "个任务在下载, 确定要退出吗?";
        }
        showPasswordDialog(message);
    }

    private void showPasswordDialog(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(name);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void getDownLoadUrl(String url, final String packageName, final boolean sort){
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDetailInfoBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDetailInfoBean response) {
                String downLoadInfo = response.getData().getAppInfo().getAppDownloadAdr();
                getDownLoadInfo(downLoadInfo, packageName, sort);
            }
        });
    }

    private void getDownLoadInfo(String url, final String packageName, final boolean sort){
        OkHttpClientUtil.getInstance()._getAsyn(url, new OkHttpClientUtil.ResultCallback<AppDownLoadBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDownLoadBean response) {
                String downLoadUrl = response.getData().getDownurl();
                Log.d(TAG, "pkg = " + packageName + " / downLoadUrl = " + downLoadUrl);
                downLoadUrlList.add(downLoadUrl);
                /*if(sort){
                    getInfoByDataBase(downLoadUrl, packageName);
                }else {
                    getInfoByNetWork(downLoadUrl, packageName);
                }*/
                if(downLoadUrlList.size() == mListAll.size()){
                    refreshAdapter();
                }
            }
        });
    }

    private ArrayList<DownLoadModel> convertList = new ArrayList<>();
    private void getInfoByDataBase(String downLoadUrl, String packageName){
        addDownLoadModel(downLoadUrl, packageName);
        Log.d(TAG, "list size = " + list.size() + " / mListAll Size = " + mListAll.size());
        if(list.size() == mListAll.size()){
            for (int i = 0; i < mListAll.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if(mListAll.get(i).getPackageName().equals(list.get(j).getInfo().getFileName())){
                        convertList.add(list.get(j));
                    }
                }
            }
            for (int k = 0; k < downloadInfoList.size(); k++){
                boolean repeat = false;
                DownloadInfo info = downloadInfoList.get(k);
                if(modelList == null){
                    DownLoadModel model = new DownLoadModel();
                    model.setInfo(info);
                    modelList.add(model);
                }else {
                    for(int m = 0; m < modelList.size(); m++){
                        if(modelList.get(m).getInfo().getFileName().equals(info.getFileName()))
                            repeat = true;
                            break;
                    }
                    if(!repeat){
                        DownLoadModel model = new DownLoadModel();
                        model.setInfo(info);
                        modelList.add(model);                    }
                }
            }
            for(int j = modelList.size(); j < convertList.size(); j++){
                modelList.add(convertList.get(j));
            }
            Log.d(TAG, "getInfoByDataBase");
            listViewAdapter.setModelList(modelList);
            //refreshAdapter();
        }
    }

    private void getInfoByNetWork(String downLoadUrl, String packageName){
        addDownLoadModel(downLoadUrl, packageName);
        Log.d(TAG, "name = " + packageName);
        if(list.size() == mListAll.size()){
            for (int i = 0; i < mListAll.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if(mListAll.get(i).getPackageName().equals(list.get(j).getInfo().getFileName())){
                        modelList.add(list.get(j));
                    }
                }
            }
            Log.d(TAG, "getInfoByNetWork");
            listViewAdapter.setModelList(modelList);
            //refreshAdapter();
        }
    }

    private void addDownLoadModel(String downLoadUrl, String packageName){
        DownloadInfo info = new DownloadInfo();
        info.setUrl(downLoadUrl);
        info.setFileSavePath(ListViewAdapter.FILEPATH);// 自定义下载路径
        info.setFileName(packageName);
        DownLoadModel model = new DownLoadModel();
        model.setInfo(info);
        list.add(model);
        /*Log.d(TAG, "name = " + packageName);
        if(list.size() == mListAll.size()){
            for (int i = 0; i < mListAll.size(); i++){
                for (int j = 0; j < list.size(); j++){
                    if(mListAll.get(i).getPackageName().equals(list.get(j).getInfo().getFileName())){
                        modelList.add(list.get(j));
                    }
                }
            }
            Log.d(TAG, "model");
            listViewAdapter.setModelList(modelList);
            refreshAdapter();
        }*/
    }
}
