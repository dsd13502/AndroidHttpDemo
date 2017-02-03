package dsd.demo.okHttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by im_dsd on 2017/2/3.
 */

public class IntermediateOkHttpDemo {

    private OkHttpClient mOkHttpClient = new OkHttpClient();

    //使用同步Get方法下载一个文件，打印他的响应头，以string形式打印响应体。
    public void getSyn() throws IOException {
        Request build = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        Call call = mOkHttpClient.newCall(build);

        Response execute = call.execute();

        //不成功则抛出异常
        if (!execute.isSuccessful()) throw new IOException("Unexpected code " + execute);

        //获取响应头
        Headers headers = execute.headers();

        //遍历响应头
        for (int i = 0; i < headers.size(); i++) {
            //根据索引，获取响应头的名称与其中的值
            Log.d("getSyn   " ,headers.name(i) + ": " + headers.value(i));
        }
        Log.d("getSyn    " ,execute.body().string());
    }


    public void getAsyn()
    {
        Request build = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
    }


}
