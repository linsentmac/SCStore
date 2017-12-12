package com.lenovo.smartShop.utils;

import android.app.Application;
import android.content.Intent;

import com.lenovo.smartShop.bean.AppListBean;
import com.tmac.filedownloader.download.DownLoadService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linsen3 on 2017/9/7.
 */

public class MyApplication extends Application {

    private ArrayList<AppListBean.DataBean.DatalistBean> mList;

    @Override
    public void onCreate() {
        super.onCreate();
        this.startService(new Intent(this, DownLoadService.class));
        mList = new ArrayList<>();
    }

    public void setDataList(List<AppListBean.DataBean.DatalistBean> list){
        if(mList.size() > 0){
            mList.clear();
        }
        mList.addAll(list);
    }

    public List<AppListBean.DataBean.DatalistBean> getDataList(){
        return mList;
    }

}
