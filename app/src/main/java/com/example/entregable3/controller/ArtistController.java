package com.example.entregable3.controller;

import com.example.entregable3.model.dao.ArtistDAO;
import com.example.entregable3.model.pojo.ArtistContainer;
import com.example.entregable3.retrofit.ResultListener;

public class ArtistController {

    public static void getArtists(final ResultListener<ArtistContainer> listener)
    {
        new ArtistDAO().getArtistsFromJSON(listener);
    }

}
