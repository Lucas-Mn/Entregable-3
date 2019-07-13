package com.example.entregable3.model.pojo;

public class Artist {

    private Integer artistId;
    private String name;
    private String nationality;
    private String Influenced_by;

    public Artist(Integer artistId, String name, String nationality, String Influenced_by)
    {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.Influenced_by = Influenced_by;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }
}
