package dsd.demo.okHttp;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 单例模式的网络请求助手
 * Created by im_dsd on 17-1-3.
 */

public class OkHttpClickManager {

    private final OkHttpClient mOkHttpClient;

    public OkHttpClickManager() {
        mOkHttpClient = new OkHttpClient();
    }

    private static class Singleton {
        static final OkHttpClickManager INSTANCE = new OkHttpClickManager();
    }

    public static OkHttpClickManager getInstance() {
        return Singleton.INSTANCE;
    }


    /**
     * 简单的异步Get请求
     *
     * @param url
     * @param callBack
     */
    public void getAsyn(@NonNull String url, final ResponseCallBack callBack) {
        Call call = getCallByHttpGet(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onResponse(call, response);
            }
        });

    }


    //同步get
    public void getSyn(@NonNull String url, final ResponseCallBack callBack) {
        Call call = getCallByHttpGet(url);
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                callBack.onResponse(call, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callBack.onFailure(call, e);
        }


    }



    //上传文件

    //下载文件

    //回调
    abstract class ResponseCallBack {
        abstract void onFailure(Call request, Exception e);

        abstract void onResponse(Call call, Response response);
    }


    /**
     * 通过获取
     * @param url
     * @return
     */
    private Call getCallByHttpGet(@NonNull String url) {
        //不指定获取方式的时候默认是get
        Request request = new Request.Builder().url(url).build();

        return mOkHttpClient.newCall(request);
    }
}
