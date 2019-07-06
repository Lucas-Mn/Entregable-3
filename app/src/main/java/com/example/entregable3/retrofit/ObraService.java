package com.example.entregable3.retrofit;

import com.example.entregable3.model.pojo.ObraContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ObraService {

    @GET("x858r")
    Call<ObraContainer> getObraContainer();

}
