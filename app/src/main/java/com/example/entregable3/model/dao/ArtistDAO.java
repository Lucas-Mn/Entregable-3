package com.example.entregable3.model.dao;

import com.example.entregable3.DEBUG;
import com.example.entregable3.model.pojo.ArtistContainer;
import com.example.entregable3.retrofit.MyRetrofit;
import com.example.entregable3.retrofit.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistDAO extends MyRetrofit {

    public ArtistDAO()
    { super();}

    public void getArtistsFromJSON(final ResultListener<ArtistContainer> listener)
    {
        Call<ArtistContainer> call = service.getArtistDetailContainer();
        call.enqueue(new Callback<ArtistContainer>() {
            @Override
            public void onResponse(Call<ArtistContainer> call, Response<ArtistContainer> response) {
                listener.finish(response.body());
            }

            @Override
            public void onFailure(Call<ArtistContainer> call, Throwable t) {
                DEBUG.LOG("getArtistsFromJSON ERROR : " + t.getMessage());
            }
        });
    }

}
