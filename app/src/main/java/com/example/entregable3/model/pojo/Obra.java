package com.example.entregable3.model.pojo;

public class Obra {

    private String image;
    private String name;
    private int artistId;

    public Obra(String image, String name, int artistId)
    {  this.image = image; this.name = name; this.artistId = artistId; }

    public String getImage() { return image; }
    public String getName() { return name; }
    public int getArtistId() { return artistId; }

    @Override
    public String toString()
    { return Integer.toString(artistId) + " : " + name; }

}
