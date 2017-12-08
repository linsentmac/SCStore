package com.lenovo.smartShop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lenovo.smartShop.R;
import com.lenovo.smartShop.view.DownLoadButton;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linsen3 on 2017/9/7.
 */

public class ViewHolder implements DownLoadButton.OnDownLoadButtonClickListener{

    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;
    private int mPosition;
    private final String TAG = "SC-ViewHolder";
    /**
     * init holder
     */
    public ViewHolder(Context context, int layoutId, ViewGroup parent, int position) {
        mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mViews = new SparseArray<>();
        mPosition = position;
        mConvertView.setTag(this);
    }

    /**
     *  获取viewHolder
     */
    public static ViewHolder getHolder(Context context, View convertView,
                                       int layoutId, ViewGroup parent, int position) {
        if(convertView == null){
            return new ViewHolder(context,layoutId,parent,position);
        }else{
            ViewHolder holder = (ViewHolder)convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public View getConvertView(){
        return mConvertView;
    }

    /**
     * get view
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * set text
     */
    public ViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     *  set image res
     */
    public ViewHolder setImageResource(int viewId,int resId){
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     *  set image bitmap
     */
    public ViewHolder setImageBitmap(int viewId,Bitmap bitmap){
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * display image
     */
    public ViewHolder setDisPlayImage(int viewId, String url){
        ImageView iv = getView(viewId);
        //OkHttpClientUtil.getInstance()._displayImage(iv, url, -1);
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
        return this;
    }

    private DownLoadButton btn_down;
    private String mUrl;
    private String packageName;
    private int position;
    private boolean mIsSearch;
    public ViewHolder setViewClick(int viewId, final int mPosition, final String appPackageName, String versionCode, final Context context, final int size, final boolean isSearch){
        btn_down = getView(viewId);
        packageName = appPackageName;
        position = mPosition;
        mIsSearch = isSearch;
        mUrl = HttpUtils.commDetailUrl + "pn=" + packageName + "&vc=" + versionCode;
        Log.d(TAG, "pre " + mPosition);
        int downLoadState = StateMachine.getInstance().getDownLoadState(appPackageName);
        switch (downLoadState){
            case DownLoadButton.STATE_NORMAL:
                Log.d(TAG, "Normal");
                btn_down.setState(appPackageName, DownLoadButton.STATE_NORMAL);
                break;
            case DownLoadButton.STATE_DOWNLOADING:
                Log.d(TAG, "Downloading");
                btn_down.setState(appPackageName, DownLoadButton.STATE_DOWNLOADING);
                break;
            case DownLoadButton.STATE_INSTALL:
                Log.d(TAG, "Install");
                btn_down.setState(appPackageName, DownLoadButton.STATE_INSTALL);
                break;
            case DownLoadButton.STATE_COMPLETE:
                Log.d(TAG, "Complete");
                btn_down.setState(appPackageName, DownLoadButton.STATE_COMPLETE);
                break;
        }
        Log.d(TAG, "position = " + mPosition + " packageName = " + appPackageName);

        //btn_down.postInvalidate();
        //btn_down.setVisibility(View.VISIBLE);
        btn_down.setOnDownLoadButtonClickListener(this);

        return this;
    }


    private TextView percentTv;
    @Override
    public void onClick(View v, int curState) {
        if (curState == DownLoadButton.STATE_NORMAL) {
            //开始下载
            btn_down.setState(packageName, DownLoadButton.STATE_DOWNLOADING);
            Log.d(TAG, "mContext = " + mContext);
            DownLoadManager.getInstance(mContext).appInfoRequest(mUrl, mContext, btn_down, position, mIsSearch);
        }else if(curState == DownLoadButton.STATE_COMPLETE){
            Log.d(TAG, "open application");
            SCPackageManager.startActivityByPn(packageName);
        }else if(curState == DownLoadButton.STATE_INSTALL){
            DownLoadManager.getInstance(mContext).installApk(new File(SCPackageManager.PATH, packageName));
        }
    }




}
