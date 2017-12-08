package com.lenovo.smartShop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.adapter.GirdViewAdapter;
import com.lenovo.smartShop.bean.AppDetailInfoBean;
import com.lenovo.smartShop.bean.AppListBean;
import com.lenovo.smartShop.utils.DataFormatConvert;
import com.lenovo.smartShop.utils.HttpUtils;
import com.lenovo.smartShop.utils.MyApplication;
import com.lenovo.smartShop.utils.OkHttpClientUtil;
import com.lenovo.smartShop.utils.StatusBarUtil;
import com.lenovo.smartShop.view.StarBar;

import java.util.List;

import okhttp3.Request;

public class AppDetailInfoActivity extends Activity implements View.OnClickListener{

    private MyApplication myApplication;
    private AppListBean.DataBean.DatalistBean datalistBean;
    private AppDetailInfoBean appDetailInfoBean;
    private GirdViewAdapter adapter;
    private final String TAG = "SC-AppDetailInfo";

    private ImageView iv_icon;
    private ImageView iv_back;
    private ImageView app_Img;
    private TextView tv_Name;
    private TextView tv_Download_Count;
    private TextView tv_APP_Size;
    private TextView tv_Introduce_Msg;
    private TextView tv_app_Score;
    private GridView gridView;
    private StarBar starBar;
    private String downloadUrl;
    private String packageName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.adjustTranslentWindow(this);
        setContentView(R.layout.activity_app_detail_info);
        StatusBarUtil.setWhiteTranslucent(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
        initEvents();
    }

    private void initView() {
        iv_icon = (ImageView) findViewById(R.id.icon_iv);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        //app_Img = (ImageView) findViewById(R.id.app_Img);
        tv_Name = (TextView) findViewById(R.id.app_name);
        tv_Download_Count = (TextView) findViewById(R.id.app_download_count);
        tv_APP_Size = (TextView) findViewById(R.id.app_size);
        tv_Introduce_Msg = (TextView) findViewById(R.id.introduce_msg);
        tv_app_Score = (TextView) findViewById(R.id.tv_app_score);
        gridView = (GridView) findViewById(R.id.gridView);
        starBar = (StarBar) findViewById(R.id.starBar);
    }

    private void initEvents() {
        // 防止抢占焦点
        gridView.setFocusable(false);
        iv_back.setOnClickListener(this);
    }

    private void initData() {
        myApplication = (MyApplication) getApplication();
        int position = getIntent().getIntExtra("position", -1);
        if(position >= 0 && myApplication.getDataList() != null){
            datalistBean = myApplication.getDataList().get(position);
            displayViewData();
        }
    }

    private void displayViewData() {
        OkHttpClientUtil.getInstance()._displayImage(iv_icon, datalistBean.getIconAddr(), -1);
        tv_Name.setText(datalistBean.getName());
        tv_Download_Count.setText(datalistBean.getDownloadCount());
        tv_APP_Size.setText(DataFormatConvert.formatData(datalistBean.getSize()));
        tv_app_Score.setText(datalistBean.getAverageStar()+"");
        starBar.setStarMark((float) datalistBean.getAverageStar());
        OkHttpClientUtil.getInstance()._getAsyn(HttpUtils.commDetailUrl + "pn=" + datalistBean.getPackageName() + "&vc=" + datalistBean.getVersioncode(), new OkHttpClientUtil.ResultCallback<AppDetailInfoBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(AppDetailInfoBean response) {
                appDetailInfoBean = response;
                String description = appDetailInfoBean.getData().getAppInfo().getDescription();
                Log.d(TAG, "Description = " + description);
                tv_Introduce_Msg.setText(description);
                downloadUrl = appDetailInfoBean.getData().getAppInfo().getAppDownloadAdr();
                packageName = appDetailInfoBean.getData().getAppInfo().getPackageName();
                displayAppImage(appDetailInfoBean.getData().getAppInfo().getFSnapList());
            }

        });
    }

    private void displayAppImage(List<AppDetailInfoBean.DataBean.AppInfoBean.FSnapListBean> list) {
        setGridViewLayout(list.size());
        adapter = new GirdViewAdapter(AppDetailInfoActivity.this, list, R.layout.gridview_item);
        gridView.setAdapter(adapter);
    }

    private final int ITEM_LENGTH = 150;
    private final int ITEM_SPACE = 20;
    private void setGridViewLayout(int size){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (ITEM_LENGTH) * density + (size - 1) * ITEM_SPACE);
        int itemWidth = (int) (ITEM_LENGTH * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(ITEM_SPACE); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
