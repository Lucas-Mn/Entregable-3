package com.example.entregable3.model.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "artists")
public class ArtistTable {

    @PrimaryKey
    private Long id;
    private String name;
    private String nationality;
    private String influence;

    @Ignore
    public ArtistTable(Long id, String name, String nationality, String influence) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.influence = influence;
    }

    public ArtistTable(){}


    public String getInfluence() { return influence; }
    public String getNationality() { return nationality; }
    public String getName() { return name; }
    public Long getId() { return id; }

    public void setInfluence(String value) { influence = value; }
    public void setNationality(String value) { nationality = value; }
    public void setName(String value) { name = value; }
    public void setId(Long value) { id = value; }

    public static ArtistTable fromPojo(ArtistDetails x)
    {
        return new ArtistTable(x.getArtistId().longValue(), x.getName(), x.getNationality(), x.getInfluenced_by());
    }

    public ArtistDetails toPojo()
    {
        return new ArtistDetails(id.intValue(), influence, name, nationality);
    }

    @Override
    public String toString()
    {
        return id + " : " + name;
    }
}
