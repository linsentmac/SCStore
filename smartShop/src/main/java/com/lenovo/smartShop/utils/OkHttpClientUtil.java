package com.lenovo.smartShop.utils;

/**
 * Created by linsen3 on 2017/9/5.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.lenovo.smartShop.R;
import com.lenovo.smartShop.activity.ShopListActivity;
import com.lenovo.smartShop.view.DownLoadButton;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class OkHttpClientUtil
{
    private static OkHttpClientUtil mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;
    private Gson mGson;


    private static final String TAG = "OkHttpClientUtil";

    private OkHttpClientUtil()
    {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
       /* mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));*/
        mDelivery = new Handler(Looper.getMainLooper());
        mGson = new Gson();


    }

    public static OkHttpClientUtil getInstance()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpClientUtil.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpClientUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return Response
     */
    public Response _getAsyn(String url) throws IOException
    {
        final Request request = new Request.Builder()
                .url(url)
                .header("Cookie", "channelid=18540")
                .header("User-Info","mfr=Lenovo;model=K910;devid=863664000004555;devidty=imei;osty=android;osver=6.0;userip=125.71.215.92")
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return 字符串
     */
    private String _getAsString(String url) throws IOException
    {
        Response execute = _getAsyn(url);
        return execute.body().string();
    }


    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    public void _getAsyn(String url, final ResultCallback callback)
    {
        final Request request = new Request.Builder()
                .url(HttpUrl.parse(url))
                .header("Cookie", "channelid=18540")
                .header("User-Info","mfr=Lenovo;model=K910;devid=863664000004555;devidty=imei;osty=android;osver=6.0;userip=125.71.215.92")
                .build();
        deliveryResult(callback, request);
    }


    /**
     * 同步的Post请求
     *
     * @param url
     * @param params post的参数
     * @return
     */
    /*private Response _post(String url, Param... params) throws IOException
    {
        Request request = buildPostRequest(url, params);
        Response response = mOkHttpClient.newCall(request).execute();
        return response;
    }*/


    /**
     * 同步的Post请求
     *
     * @param url
     * @param params post的参数
     * @return 字符串
     */
    /*private String _postAsString(String url, Param... params) throws IOException
    {
        Response response = _post(url, params);
        return response.body().string();
    }*/

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final ResultCallback callback, Param... params)
    {
        //Request request = buildPostRequest(url, params);
        //deliveryResult(callback, request);
    }

    /**
     * 异步的post请求
     *
     * @param url
     * @param callback
     * @param params
     */
    /*private void _postAsyn(String url, final ResultCallback callback, Map<String, String> params)
    {
        Param[] paramsArr = map2Params(params);
        Request request = buildPostRequest(url, paramsArr);
        deliveryResult(callback, request);
    }*/

    /**
     * 同步基于post的文件上传
     *
     * @param params
     * @return
     */
    /*private Response _post(String url, File[] files, String[] fileKeys, Param... params) throws IOException
    {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey) throws IOException
    {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        return mOkHttpClient.newCall(request).execute();
    }

    private Response _post(String url, File file, String fileKey, Param... params) throws IOException
    {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params);
        return mOkHttpClient.newCall(request).execute();
    }*/

    /**
     * 异步基于post的文件上传 多文件且携带其他form参数上传
     *
     * @param url
     * @param callback
     * @param files
     * @param fileKeys
     * @throws IOException
     */
    /*private void _postAsyn(String url, ResultCallback callback, File[] files, String[] fileKeys, Param... params) throws IOException
    {
        Request request = buildMultipartFormRequest(url, files, fileKeys, params,callback);
        deliveryResult(callback, request);
    }*/

    /**
     * 异步基于post的文件上传，单文件不带参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @throws IOException
     */
    /*private void _postAsyn(String url, ResultCallback callback, File file, String fileKey) throws IOException
    {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null,callback);
        deliveryResult(callback, request);
    }*/

    /**
     * 异步基于post的文件上传，单文件且携带其他form参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @param params
     * @throws IOException
     */
    /*private void _postAsyn(String url, ResultCallback callback, File file, String fileKey, Param... params) throws IOException
    {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, params, callback);
        deliveryResult(callback, request);
    }*/

    /**
     * 异步下载文件
     *
     * @param url
     * @param destFileDir 本地文件存储的文件夹
     * @param callback
     */
    private void _downloadAsyn(final String url, final String destFileDir, final ResultCallback callback, final DownLoadButton downLoadButton, final int position, final boolean isSearch)
    {

        //增加拦截器
        /*mOkHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截
                Response originalResponse = chain.proceed(chain.request());
                //包装响应体并返回
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), callback))
                        .build();
            }
        });*/

        final Request request = new Request.Builder()
                .url(url)
                .build();
        downloadCall = mOkHttpClient.newCall(request);
        downloadCall.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try{
                    is = response.body().byteStream();
                    int length = (int) response.body().contentLength();
                    Log.d(TAG, "length = " + length);
                    File rootPath = new File(SCPackageManager.PATH);
                    if(!rootPath.exists()){
                        rootPath.mkdirs();
                    }
                    File file = new File(rootPath, destFileDir);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    int total = 0;
                    int tem = 0;
                    while ((len = is.read(buf)) != -1)
                    {
                        fos.write(buf, 0, len);
                        total += len;
                        // 获取当前下载量
                        int percent = total/(length/100);
                        Log.d(TAG, "total = " + total + " /length = " + length + " /percent = " + percent);
                        //downLoadButton.setDownLoadProgress(DownLoadButton.STATE_DOWNLOADING, percent);

                        ListView listView = ShopListActivity.listView;
                        if (percent > tem && position >= listView.getFirstVisiblePosition() && position <= listView.getLastVisiblePosition()) {
                            tem = percent;
                            int positionInListView = position - listView.getFirstVisiblePosition();
                            DownLoadButton btn = listView.getChildAt(positionInListView).findViewById(R.id.btn_item);
                            btn.setDownLoadProgress(DownLoadButton.STATE_DOWNLOADING, percent, destFileDir);
                        }
                    }
                    fos.flush();
                    //如果下载文件成功，第一个参数为文件的绝对路径
                    sendSuccessResultCallback(file, callback);
                    downLoadButton.setState(destFileDir, DownLoadButton.STATE_INSTALL);
                } catch (IOException e){
                    sendFailedStringCallback(response.request(), e, callback);
                } finally{
                    try{
                        if (is != null) is.close();
                        if (fos != null) fos.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

        });
    }


    private String getFileName(String path)
    {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }


//    public void displayImage(final ImageView view, final String path){
//    	new Thread(new Runnable() {
//			@Override
//			public void run() {
//				final Bitmap bitmap = BitmapHelper.compressBitmap(path, view.getWidth(), view.getHeight());
//				mDelivery.post(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                    	view.setImageBitmap(bitmap);
//                    }
//                });
//			}
//		}).start();
//    }

    /**
     * 加载图片
     *
     * @param view
     * @param url
     * @throws IOException
     */
    public void _displayImage(final ImageView view, final String url, final int errorResId)
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                setErrorResId(view, errorResId);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                try
                {
                    is = response.body().byteStream();
                    // TODO  计算压缩比率
//                    ImageUtils.ImageSize actualImageSize = ImageUtils.getImageSize(is);
//                    ImageUtils.ImageSize imageViewSize = ImageUtils.getImageViewSize(view);
//                    int inSampleSize = ImageUtils.calculateInSampleSize(actualImageSize, imageViewSize);
                    try
                    {
                        is.reset();
                    } catch (IOException e)
                    {
                        response = _getAsyn(url);
                        is = response.body().byteStream();
                    }

                    BitmapFactory.Options ops = new BitmapFactory.Options();
                    ops.inJustDecodeBounds = false;
                    ops.inSampleSize = 1;  // inSampleSize
                    final Bitmap bm = BitmapFactory.decodeStream(is, null, ops);
                    mDelivery.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            view.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e)
                {
                    setErrorResId(view, errorResId);

                } finally
                {
                    if (is != null) try
                    {
                        is.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        });


    }

    private void setErrorResId(final ImageView view, final int errorResId)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                view.setImageResource(errorResId);
            }
        });
    }


    //*************对外公布的方法************


    public static Response getAsyn(String url) throws IOException
    {
        return getInstance()._getAsyn(url);
    }


    public static String getAsString(String url) throws IOException
    {
        return getInstance()._getAsString(url);
    }

    public static void getAsyn(String url, ResultCallback callback)
    {
        getInstance()._getAsyn(url, callback);
    }

    /*public static Response post(String url, Param... params) throws IOException
    {
        return getInstance()._post(url, params);
    }

    public static String postAsString(String url, Param... params) throws IOException
    {
        return getInstance()._postAsString(url, params);
    }*/

    public static void postAsyn(String url, final ResultCallback callback, Param... params)
    {
        getInstance()._postAsyn(url, callback, params);
    }


    public static void postAsyn(String url, final ResultCallback callback, Map<String, String> params)
    {
        //getInstance()._postAsyn(url, callback, params);
    }


    /*public static Response post(String url, File[] files, String[] fileKeys, Param... params) throws IOException
    {
        return getInstance()._post(url, files, fileKeys, params);
    }

    public static Response post(String url, File file, String fileKey) throws IOException
    {
        return getInstance()._post(url, file, fileKey);
    }

    public static Response post(String url, File file, String fileKey, Param... params) throws IOException
    {
        return getInstance()._post(url, file, fileKey, params);
    }

    public static void postAsyn(String url, ResultCallback callback, File[] files, String[] fileKeys, Param... params) throws IOException
    {
        getInstance()._postAsyn(url, callback, files, fileKeys, params);
    }


    public static void postAsyn(String url, ResultCallback callback, File file, String fileKey) throws IOException
    {
        getInstance()._postAsyn(url, callback, file, fileKey);
    }


    public static void postAsyn(String url, ResultCallback callback, File file, String fileKey, Param... params) throws IOException
    {
        getInstance()._postAsyn(url, callback, file, fileKey, params);
    }*/

//    public static void displayImage(final ImageView view, String url, int errorResId) throws IOException
//    {
//        getInstance()._displayImage(view, url, errorResId);
//    }
//
//
//    public static void displayImage(final ImageView view, String url)
//    {
//        getInstance()._displayImage(view, url, -1);
//    }

    public static void downloadAsyn(String url, String destDir, ResultCallback callback, DownLoadButton downLoadButton, int position, boolean isSearch)
    {
        getInstance()._downloadAsyn(url, destDir, callback, downLoadButton, position, isSearch);
    }

    private static Call downloadCall;
    public static void cancelDownload(){
        if(downloadCall != null){
            if(!downloadCall.isCanceled()){
                downloadCall.cancel();
            }
        }
    }

    public static void cancelRequest(){
        if(requestCall != null){
            if(!requestCall.isCanceled()){
                requestCall.cancel();
            }
        }

    }

    //****************************


    /*private Request buildMultipartFormRequest(String url, File[] files,
                                              String[] fileKeys, Param[] params)
    {
        params = validateParam(params);

        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);

        for (Param param : params)
        {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(null, param.value));
        }
        if (files != null)
        {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++)
            {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                        fileBody);// new ProgressRequestBody(requestBody,progressRequestListener)
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }*/

    //用于异步的带回调
    /*private Request buildMultipartFormRequest(String url, File[] files,
                                              String[] fileKeys, Param[] params,ResultCallback callback)
    {
        params = validateParam(params);

        MultipartBuilder builder = new MultipartBuilder()
                .type(MultipartBuilder.FORM);

        for (Param param : params)
        {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(null, param.value));
        }
        if (files != null)
        {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++)
            {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                        new ProgressRequestBody(fileBody,callback));// new ProgressRequestBody(requestBody,progressRequestListener)
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }*/

    private String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    private Param[] validateParam(Param[] params)
    {
        if (params == null)
            return new Param[0];
        else return params;
    }

    private Param[] map2Params(Map<String, String> params)
    {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries)
        {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }


    private static Call requestCall;
    private void deliveryResult(final ResultCallback callback, final Request request)
    {
        requestCall =  mOkHttpClient.newCall(request);
        requestCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure");
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse = ");
                try {
                    final String string = response.body().string();
                    Log.d(TAG, "onResponse = " + string);
//                    Log.d("deliveryResult","response >"+string);
                    if (callback.mType == String.class) {
                        sendSuccessResultCallback(string, callback);
                    } else {
                        Log.d(TAG, "onResponse match type");
                        Object o = mGson.fromJson(string, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }


                } catch (IOException e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }

        });


    }



    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final ResultCallback callback)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                {
                    callback.onResponse(object);
                }
            }
        });
    }

    /*private Request buildPostRequest(String url, Param[] params)
    {
        if (params == null)
        {
            params = new Param[0];
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Param param : params)
        {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }*/


    public static abstract class ResultCallback<T>
    {
        Type mType;

        public ResultCallback()
        {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass)
        {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class)
            {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);

        /**
         * 响应体进度回调接口，比如用于文件下载中
         * User:lizhangqu(513163535@qq.com)
         * Date:2015-09-02
         * Time: 17:16
         */
        public void onResponseProgress(long bytesRead, long contentLength, boolean done){};

        /**
         * 请求体进度回调接口，比如用于文件上传中
         * User:lizhangqu(513163535@qq.com)
         * Date:2015-09-02
         * Time: 17:16
         */
        public void onRequestProgress(long bytesWritten, long contentLength, boolean done){};
    }


    public static class Param
    {
        public Param()
        {
        }

        public Param(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }

    /////////////////////////////////////////////////////////////////////
    // Android OkHttp文件上传与下载的进度监听扩展	( http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0904/3416.html )
    /**
     * 包装的响体，处理进度    下载是继承ResponseBody
     * User:lizhangqu(513163535@qq.com)
     * Date:2015-09-02
     * Time: 17:18
     */
    public class ProgressResponseBody extends ResponseBody {
        //实际的待包装响应体
        private final ResponseBody responseBody;
        //进度回调接口
        private final ResultCallback progressListener;
        //包装完成的BufferedSource
        private BufferedSource bufferedSource;

        /**
         * 构造函数，赋值
         * @param responseBody 待包装的响应体
         * @param progressListener 回调接口
         */
        public ProgressResponseBody(ResponseBody responseBody, ResultCallback progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }


        /**
         * 重写调用实际的响应体的contentType
         * @return MediaType
         */
        @Override public MediaType contentType() {
            return responseBody.contentType();
        }

        /**
         * 重写调用实际的响应体的contentLength
         * @return contentLength
         * @throws IOException 异常
         */
        @Override public long contentLength() {
            return responseBody.contentLength();
        }

        /**
         * 重写进行包装source
         * @return BufferedSource
         * @throws IOException 异常
         */
        @Override public BufferedSource source() {
            if (bufferedSource == null) {
                //包装
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        /**
         * 读取，回调进度接口
         * @param source Source
         * @return Source
         */
        private Source source(Source source) {

            return new ForwardingSource(source) {
                //当前读取字节数
                long totalBytesRead = 0L;
                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    final long bytesRead = super.read(sink, byteCount);
                    //增加当前读取的字节数，如果读取完成了bytesRead会返回-1
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    //回调，如果contentLength()不知道长度，会返回-1
                    mDelivery.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(progressListener != null)
                                progressListener.onResponseProgress(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                        }
                    });
                    return bytesRead;
                }
            };
        }
    }


    /**
     * 包装的请求体，处理进度   上传就是继承RequestBody
     * User:lizhangqu(513163535@qq.com)
     * Date:2015-09-02
     * Time: 17:15
     */
    public  class ProgressRequestBody extends RequestBody {
        //实际的待包装请求体
        private final RequestBody requestBody;
        //进度回调接口
        private final ResultCallback progressListener;
        //包装完成的BufferedSink
        private BufferedSink bufferedSink;

        /**
         * 构造函数，赋值
         * @param requestBody 待包装的请求体
         * @param progressListener 回调接口
         */
        public ProgressRequestBody(RequestBody requestBody, ResultCallback progressListener) {
            this.requestBody = requestBody;
            this.progressListener = progressListener;
        }

        /**
         * 重写调用实际的响应体的contentType
         * @return MediaType
         */
        @Override
        public MediaType contentType() {
            return requestBody.contentType();
        }

        /**
         * 重写调用实际的响应体的contentLength
         * @return contentLength
         * @throws IOException 异常
         */
        @Override
        public long contentLength() throws IOException {
            return requestBody.contentLength();
        }

        /**
         * 重写进行写入
         * @param sink BufferedSink
         * @throws IOException 异常
         */
        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            if (bufferedSink == null) {
                //包装
                bufferedSink = Okio.buffer(sink(sink));
            }
            //写入
            requestBody.writeTo(bufferedSink);
            //必须调用flush，否则最后一部分数据可能不会被写入
            bufferedSink.flush();

        }

        /**
         * 写入，回调进度接口
         * @param sink Sink
         * @return Sink
         */
        private Sink sink(Sink sink) {
            return new ForwardingSink(sink) {
                //当前写入字节数
                long bytesWritten = 0L;
                //总字节长度，避免多次调用contentLength()方法
                long contentLength = 0L;

                @Override
                public void write(Buffer source, long byteCount) throws IOException {
                    super.write(source, byteCount);
                    if (contentLength == 0) {
                        //获得contentLength的值，后续不再调用
                        contentLength = contentLength();
                    }
                    //增加当前写入的字节数
                    bytesWritten += byteCount;
                    //回调
                    mDelivery.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            if(progressListener != null)
                                progressListener.onRequestProgress(bytesWritten, contentLength, bytesWritten == contentLength);
                        }
                    });
                }
            };
        }
    }


}

