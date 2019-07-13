package com.example.entregable3.model.pojo;

public class ArtistDetails {

    private Integer artistId;
    private String influenced_by;
    private String name;
    private String nationality;

    public ArtistDetails(){}
    public ArtistDetails(Integer artistId, String influenced_by, String name, String nationality) {
        this.artistId = artistId;
        this.influenced_by = influenced_by;
        this.name = name;
        this.nationality = nationality;
    }


    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }

    public String getInfluenced_by() {
        return influenced_by;
    }

    public Integer getArtistId() {
        return artistId;
    }
}
