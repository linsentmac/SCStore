package com.lenovo.smartShop.adapter;

import android.content.Context;
import android.util.Log;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.bean.AppListBean;
import com.lenovo.smartShop.utils.DataFormatConvert;
import com.lenovo.smartShop.utils.ViewHolder;

import java.util.List;

/**
 * Created by linsen3 on 2017/9/7.
 */

public class SearchAdapter extends CommonAdapter<AppListBean.DataBean.DatalistBean> {


    public SearchAdapter(Context context, List<AppListBean.DataBean.DatalistBean> data, int layoutId, boolean isSearch) {
        super(context, data, layoutId, isSearch);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        Log.d("LS-Adapter", "position = " + position);
        holder.setText(R.id.tv_item, mData.get(position).getName())
                .setText(R.id.tv_item_download, mData.get(position).getDownloadCount())
                .setText(R.id.tv_item_size, DataFormatConvert.formatData(mData.get(position).getSize()))
                .setText(R.id.tv_item_desc, mData.get(position).getTypeName())
                .setDisPlayImage(R.id.iv_item, mData.get(position).getIconAddr())
                .setViewClick(R.id.btn_item, position, mData.get(position).getPackageName(), mData.get(position).getVersioncode(), mContext, mData.get(position).getSize(), mIsSearch);
    }
}
