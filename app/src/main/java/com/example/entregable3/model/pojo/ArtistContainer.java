package com.example.entregable3.model.pojo;

import java.util.List;

public class ArtistContainer {

    List<Artist> artists;

    public ArtistContainer(List<Artist> artists){ this.artists = artists; }

    public List<Artist> getArtists(){return artists;}

}
