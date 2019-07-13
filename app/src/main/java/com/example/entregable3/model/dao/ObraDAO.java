package com.example.entregable3.model.dao;

import com.example.entregable3.DEBUG;
import com.example.entregable3.model.pojo.ObraContainer;
import com.example.entregable3.retrofit.MyRetrofit;
import com.example.entregable3.retrofit.MyService;
import com.example.entregable3.retrofit.ResultListener;
import com.example.entregable3.util.FoundListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObraDAO extends MyRetrofit {

    public ObraDAO()
    { super(); }

    public void getObras(final FoundListener<ObraContainer> listener)
    {
        Call<ObraContainer> call = service.getObraContainer();
        call.enqueue(new Callback<ObraContainer>() {
            @Override
            public void onResponse(Call<ObraContainer> call, Response<ObraContainer> response) {
                listener.onFound(response.body());
            }

            @Override
            public void onFailure(Call<ObraContainer> call, Throwable t) {
                DEBUG.LOG("lol failed: " + t.getMessage());
                listener.onCancelled();
            }
        });
    }

}
