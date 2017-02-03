package dsd.demo.okHttp;


import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 高级OkHttp使用
 * Created by im_dsd on 17-1-12.
 */

public class SeniorOkHttpDemo {
    OkHttpClient mOkHttpClient = new OkHttpClient();

    // TODO: 17-1-12 同步的get
    public void getSynHttp() throws IOException {
        //step 1： 获取Requst
        Request build = new Request.Builder().url("https://www.baidu.com/").build();

        //step 2: 建立 call
        Call call = mOkHttpClient.newCall(build);

        //step 3：创建同步请求
        Response execute = call.execute();

        //step 4：判读是成功
        if (execute.isSuccessful()) {
            // 成功后获取返回体
            ResponseBody body = execute.body();
            //我们可以获取我们想要的 格式
            String string = body.string();
            InputStream inputStream = body.byteStream();
            Reader reader = body.charStream();

        }

    }
    // TODO: 17-1-12  设置请求头
    public void setRequstHeader()
    {

        new Request.Builder().url("https://www.baidu.com/")
                .header("name","dsd");//设置头，在看源码之后发现，这个方法会删除以存在看的“key”
//                .addHeader()
    }

    // TODO: 17-1-12 提取响应头 

    // TODO: 17-1-12 缓存

    // TODO: 17-1-12 post

    // TODO: 17-1-12 设置超时时间
}
