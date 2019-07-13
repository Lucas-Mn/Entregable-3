package com.example.entregable3.retrofit;

import com.example.entregable3.model.pojo.ArtistContainer;
import com.example.entregable3.model.pojo.ObraContainer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("x858r")
    Call<ObraContainer> getObraContainer();

    @GET("vt5lf")
    Call<ArtistContainer> getArtistDetailContainer();

}
