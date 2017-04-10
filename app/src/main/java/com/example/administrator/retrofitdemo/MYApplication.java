package com.example.administrator.retrofitdemo;

import android.app.Application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/10.
 */

public class MYApplication extends Application {
    public static final String Url = "http://52.197.62.253/index.php/Home/";
    private static ServerApi serverApi;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .build();
        serverApi = retrofit.create(ServerApi.class);
    }

    public static ServerApi getServerApi() {
        return serverApi;
    }

    /**
     * 统一加header
     *
     * @return
     */
    private OkHttpClient genericClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
                requestBuilder.addHeader("User-Agent", System.getProperty("http.agent"));//System.getProperty("http.agent")
                requestBuilder.addHeader("Content-Type", "application/json;charset=UTF-8");
                requestBuilder.addHeader("Accept", "application/json; charset=utf-8; application/vnd.lanjing.v21");
                return chain.proceed(request);
            }
        });
        return builder.build();
    }
}
