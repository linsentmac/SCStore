package com.lenovo.smartShop.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

import java.io.IOException;

/**
 * Created by tmac on 17-10-17.
 */

public class WifiControl {

    private static final String TAG = "SC-WifiControl";

    private static Context mContext;
    private static boolean isConnected;
    private static WifiControl mInstance;
    public WifiControl(Context context){
        this.mContext = context;
    }

    public static WifiControl getInstance(Context context){
        if(mInstance == null){
            mInstance = new WifiControl(context);
        }
        return mInstance;
    }


    public static boolean wifiIsConnected(){
        ConnectivityManager connectMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        //NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if ( wifiNetInfo.isConnected() && wifiNetInfo.getState() == NetworkInfo.State.CONNECTED) {// unconnect network
            isConnected = true;
            Log.d(TAG, "isConnected");
        } else {
            isConnected = false;
            Log.d(TAG, "isDisConnected");
        }
        return isConnected;
    }

    //ping网络连接，看是否Intent可用。参数-c 1是指ping的次数为1次，-w是指超时时间单位为s
    public static boolean pingIpAddress(final String ipAddress) {
        Log.e(TAG, "----- Start ping ip address: " + ipAddress + " -----");

        //启动线程去ping，防止堵塞
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec("/system/bin/ping -c 3 -w 10 " + ipAddress);
                    int status = process.waitFor();
                    Log.e(TAG, "----- Ping ip address success, status = " + status + " -----");
                    if (status != 0) {
                        //Intent ping不通

                    }
                } catch (IOException | InterruptedException e) {
                    Log.e(TAG, "----- Ping ip address exception, e = " + e.toString() + " -----");
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Process process = Runtime.getRuntime().exec("/system/bin/ping -c 3 -w 10 " + ipAddress);
            int status = process.waitFor();
            Log.e(TAG, "----- Ping ip address success, status = " + status + " -----");
            if (status != 0) {
                //Intent ping不通
                return false;
            }
        } catch (IOException | InterruptedException e) {
            Log.e(TAG, "----- Ping ip address exception, e = " + e.toString() + " -----");
            e.printStackTrace();
        }
        return true;
    }
}
