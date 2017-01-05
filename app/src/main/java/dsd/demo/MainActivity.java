package dsd.demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void getAsynHttp() {
        // step 1: 创建 OkHttpClient 对象
        OkHttpClient okHttpClient = new OkHttpClient();
        // step 2： 创建一个请求
        Request.Builder requestBuilder = new Request.Builder()
                .url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
                //获得返回体
                ResponseBody body = response.body();
                String string = body.string();


                byte[] bytes = body.bytes();
                InputStream inputStream = body.byteStream();
                Reader reader = body.charStream();


            }
        });
    }


    public void getAsynPost() {
        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()
                .add("name", "dsd")
                .build();

        //step 3: 创建请求
        Request request = new Request.Builder().url("http://www.baidu.com")
                .post(formBody)
                .build();

        //step 4： 建立联系 创建Call对象
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
            }
        });
    }


    //异步上传文件
    public void postAsynFile() {
        // step 1: 创建 OkHttpClient 对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2:创建 RequestBody 以及所需的参数
        //2.1 获取文件
        File file = new File(Environment.getExternalStorageDirectory() + "test.txt");
        //2.2 创建 MediaType 设置上传文件类型
        MediaType MEDIATYPE = MediaType.parse("text/plain; charset=utf-8");
        //2.3 获取请求体
        RequestBody requestBody = RequestBody.create(MEDIATYPE, file);

        //step 3：创建请求
        Request request = new Request.Builder().url("http://www.baidu.com")
                .post(requestBody)
                .build();

        //step 4 建立联系
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 17-1-4  请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 17-1-4 请求成功
            }
        });

    }


    //异步下载文件

    private void downloadAsynFile() {
        //step 1: 老死不变的第一步创建 OkHttpClick
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建Requset
        Request request = new Request.Builder()
                .url("http://www.ssyer.com/uploads/org_2017010593503_775.jpg")
                .build();

        //step 3:建立联系，创建Call
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    File file = new File(Environment.getExternalStorageDirectory() + "大狮子.jpg");
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("downloadAsynFile", "文件下载成功");
            }
        });
    }


}
