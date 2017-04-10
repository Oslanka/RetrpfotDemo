package com.example.administrator.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/4/10.
 */

public interface ServerApi {

    @FormUrlEncoded
    @POST("test/test")
    Call<BaseBean<List<Bean>>> test(@Field("key") String first);
}
