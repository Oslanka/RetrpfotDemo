package com.example.administrator.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MYApplication.getServerApi().test("afsdfsdf").enqueue(new Callback<BaseBean<List<Bean>>>() {
            @Override
            public void onResponse(Call<BaseBean<List<Bean>>> call, Response<BaseBean<List<Bean>>> response) {
                BaseBean<List<Bean>> body = response.body();
                int code = body.code;
                String info = body.info;
                List<Bean> data = body.data;
                if (data.size()>0){
                    Toast.makeText(MainActivity.this, ""+data.get(0).getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseBean<List<Bean>>> call, Throwable t) {

            }
        });
    }
}
