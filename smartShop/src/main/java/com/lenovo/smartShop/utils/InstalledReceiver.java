package com.lenovo.smartShop.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lenovo.smartShop.view.DownLoadButton;

/**
 * Created by linsen3 on 2017/9/25.
 */

public class InstalledReceiver extends BroadcastReceiver {

    private final static int PACKAGE_INDEX = 1;
    private final static String TAG = "SC-InstalledReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String packageName = intent.getDataString().split(":")[1];
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   // uninstall
            //String packageName = intent.getDataString();
            Log.i(TAG, "卸载了 :" + packageName);
            SCPackageManager.deleteInstallPackage(packageName);
            StateMachine.getInstance().setDownLoadState(packageName, DownLoadButton.STATE_NORMAL);
            StateMachine.getInstance().refreshAdapter();
        }else if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){
            //String packageName = intent.getDataString();
            Log.i(TAG, "安装了 :" + packageName);
            SCPackageManager.deleteInstallPackage(packageName);
            StateMachine.getInstance().setDownLoadState(packageName, DownLoadButton.STATE_COMPLETE);
            StateMachine.getInstance().refreshAdapter();
        }else if(intent.getAction().equals("android.intent.action.PACKAGES_SUSPENDED")){
            Log.i(TAG, "包被停用了 :" + packageName);
        }
    }
}
