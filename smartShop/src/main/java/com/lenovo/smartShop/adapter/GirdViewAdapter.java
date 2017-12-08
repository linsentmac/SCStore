package com.lenovo.smartShop.adapter;

import android.content.Context;
import android.util.Log;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.utils.ViewHolder;

import java.util.List;

/**
 * Created by linsen3 on 2017/9/8.
 */

public class GirdViewAdapter extends CommonAdapter<AppDetailInfoBean.DataBean.AppInfoBean.FSnapListBean> {

    private final String TAG = "SC-GirdViewAdapter";

    public GirdViewAdapter(Context context, List<AppDetailInfoBean.DataBean.AppInfoBean.FSnapListBean> data, int layoutId) {
        super(context, data, layoutId, false);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        Log.d(TAG, "img_path = " + mData.get(position).getAPPIMG_PATH());
        holder.setDisPlayImage(R.id.gridView_iv, mData.get(position).getAPPIMG_PATH());
    }
}
