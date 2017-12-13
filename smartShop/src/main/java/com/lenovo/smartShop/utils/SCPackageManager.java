package com.lenovo.smartShop.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by linsen3 on 2017/9/12.
 */

public class SCPackageManager {

    private static Context mContext;
    private static String appPackageName;
    private final static String TAG = "SC-PackageManager";
    public static String PATH = Environment.getExternalStorageDirectory() + "/SmartShop/smartcast/FILETEMP/";
    private final static int PACKAGE_INDEX = 1;
    public final static int FILE_EXIST = 2;
    public final static int FILE_NOT_EXIST = 3;
    public final static int BAD_FILE = 4;

    /**
     * 查询app是否已安装存在
     * @param context
     * @param appPackageName
     * @return
     */
    public static boolean queryAppIsExist(Context context, String appPackageName)
    {
        mContext = context;
        PackageManager pm = mContext.getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> listAppcations = pm
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);// GET_UNINSTALLED_PACKAGES代表已删除，但还有安装目录的
        String activityName = "";
        String pkgName = "";
        String appLabel = "";
        for (ApplicationInfo app : listAppcations)
        {
            pkgName = app.packageName; // 获得应用程序的包名
            appLabel = (String) app.loadLabel(pm); // 获得应用程序的Label
            System.out.println("2、" + appLabel + " activityName---"
                    + activityName + " pkgName---" + pkgName);
            // Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
            if (pkgName.equalsIgnoreCase(appPackageName))
            {
                System.out.println(appLabel + " pkgName---" + pkgName);
                //appPackageName = pkgName;
                return true;
            }
        }
        return false;
    }

    public static boolean isAppInstalled(Context context, String appPackageName){
        mContext = context;
        PackageManager pm = mContext.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(appPackageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            installed = false;
        }
        return installed;
    }


    public static int queryAppInstallPackage(String packageName, int size, String md5Str){
        File specItemDir = new File(PATH);
        File[] files = specItemDir.listFiles();
        if(files != null){
            for (File file : files){
                Log.d(TAG, "name = " + file.getName());
                //getPackageSize(file);
                if(file.getName().equals(packageName)){
                    Log.d(TAG, "original size = " + size);
                    if(getPackageSize(file) >= size
                            && getFileMD5(file).equals(md5Str.toUpperCase())){
                        return FILE_EXIST;
                    }else {
                        deleteInstallPackage(packageName);
                        return BAD_FILE;
                    }
                }
            }
        }
        return FILE_NOT_EXIST;
    }

    public static boolean queryAppIsNotInstall(File[] files, String packageName){
        for (File file : files){
            Log.d(TAG, "name = " + file.getName());
            if(file.getName().equals("(" + packageName + ")" + packageName)){
                return true;
            }
        }
        return false;
    }

    public static void queryAppInstallPackage(final String packageName, final int size, final String md5Str, final QueryInstallPackage callback){
        File specItemDir = new File(PATH);
        final File[] files = specItemDir.listFiles();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(files != null){
                    for (File file : files){
                        Log.d(TAG, "name = " + file.getName());
                        //getPackageSize(file);
                        if(file.getName().equals("(" + packageName + ")" + packageName)){
                            Log.d(TAG, "original size = " + size);
                            String md5File = getFileMD5(file);
                            if(getPackageSize(file) >= size
                                    && md5File != null
                                    && md5File.equals(md5Str.toUpperCase())){
                                callback.onResponseState(FILE_EXIST);
                            }else {
                                deleteInstallPackage(packageName);
                                callback.onResponseState(BAD_FILE);
                            }
                        }
                    }
                }
            }
        }).start();

    }

    private static int getPackageSize(File file){
        long size = 0;
        if(file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                size = fis.getChannel().size();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Log.d(TAG, "file is not exist");
        }
        Log.d(TAG, "file size = " + size);
        return (int) size;
    }

    private static boolean isPackageArchive(String appPackageName){
        boolean result = false;
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(appPackageName, PackageManager.GET_ACTIVITIES);
            if(info != null){
                result = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void deleteInstallPackage(String packageName){
        //String[] sourceStrArray = packageName.split(":");
        File file = new File(PATH, "(" + packageName + ")" + packageName);
        try {
            if(file.exists()){
                file.delete();
                Log.d(TAG, "delete success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void startActivityByPn(String appPackageName){
        Intent intent = mContext.getPackageManager()
                .getLaunchIntentForPackage(appPackageName);
        if (intent != null)
            // 已安装包 直接启动
            mContext.startActivity(intent);
    }

    private void startActivityByPnAndAn(String appPackageName, String appAcitivityName){
        // 前名一个参数是应用程序的包名,后一个是这个应用程序的Activity名
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(appPackageName,
                appAcitivityName));
        mContext.startActivity(intent);
    }

    /**
     * 获取apk安装包的md5
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bytesToHexString(digest.digest());
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        Log.d(TAG, "Apk file md5 = " + stringBuilder.toString().toUpperCase());
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 获取已安装apk的md5
     * @param appPackageName
     * @return
     */
    public static String getSignMd5Str(String appPackageName){
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(appPackageName, PackageManager.GET_SIGNATURES);
            Signature[] signatures = info.signatures;
            Signature signature = signatures[0];
            String signStr = encryptionMD5(signature.toByteArray());
            Log.d(TAG, "md5Str = " + signStr);
            return signStr;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "error = " + e.getMessage().toString());
        }
        return null;
    }

    /**
     * MD5加密
     * @param byteStr 需要加密的内容
     * @return 返回 byteStr的md5值
     */
    public static String encryptionMD5(byte[] byteStr) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(byteStr);
            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5StrBuff.toString();
    }

    public static abstract class QueryInstallPackage{
        public void onResponseState(int state){}
    }

}
