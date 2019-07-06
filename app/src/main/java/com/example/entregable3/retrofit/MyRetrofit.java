package com.example.entregable3.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {

    protected Retrofit retrofit;
    protected ObraService service;

    public MyRetrofit()
    {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        Retrofit.Builder builder =new Retrofit.Builder().baseUrl(RETRO.baseURL)
                .client(client.build()).addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        service = retrofit.create(ObraService.class);
    }
}
