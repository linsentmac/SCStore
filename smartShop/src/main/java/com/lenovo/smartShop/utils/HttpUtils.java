package com.lenovo.smartShop.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by linsen3 on 2017/9/4.
 */

public class HttpUtils {

    public static String listUrl = "http://ams.lenovomm.com/ams/api/applist4thirdpartyv3?si=1&c=100&lt=subject&cg=root&code=22680";
    public static String detailUrl = "http://ams.lenovomm.com/ams/api/appinfo4thirdparty?pn=com.gotokeep.keep&vc=2951";
    //public static String downLoadUrl = "http://ams.lenovomm.com/ams/3.0/appdownaddress4thirdparty.do?pn=com.lenovo.smartcast&vc=1&ty=2&dt=1";
    public static String downLoadUrl = "http://ams.lenovomm.com/ams/3.0/appdownaddress4thirdparty.do?l=zh-CN&pn=com.gotokeep.keep&vc=2951&wr=0&dp=0&ty=2&ept=0&forceFreeDownFlag=0";
    public static String commDetailUrl = "http://ams.lenovomm.com/ams/api/appinfo4thirdparty?";

    private final static String myCookie = "channelid=18540";
    public static final String TAG = "SmartHttp";
    private static HttpUtils httpUtils;
    private static Handler mHandler;
    private String response;

    private HttpUtils(){

    }

    public static HttpUtils getInstance(Handler handler){
        mHandler = handler;
        if(httpUtils == null){
            synchronized (HttpUtils.class){
                httpUtils = new HttpUtils();
            }
        }
        return httpUtils;
    }

    public void sendRequestWithHttpClient(final String url, final int code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //用HttpClient发送请求，分为五步
                //第一步：创建HttpClient对象
                HttpClient httpCient = new DefaultHttpClient();
                //第二步：创建代表请求的对象,参数是访问的服务器地址
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("Cookie", myCookie);
                httpGet.addHeader("User-Info","mfr=Lenovo;model=K910;devid=863664000004555;devidty=imei;osty=android;osver=6.0;userip=125.71.215.92");


                try {
                    //第三步：执行请求，获取服务器发还的相应对象
                    HttpResponse httpResponse = httpCient.execute(httpGet);
                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        //第五步：从相应对象当中取出数据，放到entity当中
                        HttpEntity entity = httpResponse.getEntity();
                        response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串
                        Log.d(TAG, "Http request success : " + response);
                    }else {
                        Log.d(TAG, "Http request fail code : " + httpResponse.getStatusLine().getStatusCode());
                        HttpEntity entity = httpResponse.getEntity();
                        response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串
                        Log.d(TAG, "Http request fail message : " + response);
                    }
                    Message message = new Message();
                    message.obj = response;
                    message.what = code;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.d(TAG, "Exception : " + e.getMessage().toString());
                }
            }
        }).start();
    }


}
