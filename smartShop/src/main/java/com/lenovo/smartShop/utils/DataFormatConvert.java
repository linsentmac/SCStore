package com.lenovo.smartShop.utils;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by linsen3 on 2017/9/8.
 */

public class DataFormatConvert {

    private static final String TAG = "SC-DataFormatConvert";

    public static String formatData(int size){
        float sizeF = (float) size/1024/1024;
        DecimalFormat df = new DecimalFormat("0.00");
        String appSize = df.format(sizeF) + "M";
        Log.d(TAG, "size = " + size + " / appSize = " + appSize);
        return appSize;
    }

}
