package com.lenovo.smartShop.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

/**
 * Created by tmac on 17-10-13.
 */

public class WifiStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
            // open or close

        }else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
            // connect or discennect

        }
    }
}
