package com.example.entregable3.model.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "obras")
public class ObraTable {

    @PrimaryKey
    private Long id;
    private String name;
    private Long artistId;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public ObraTable(){}

    public Long getId(){return id;}
    public String getName(){return name;}
    public Long getArtistId(){return artistId;}
    public byte[] getImage(){return image;}

    public void setId(Long value){id = value;}
    public void setName(String value){name = value;}
    public void setArtistId(Long value){artistId = value;}
    public void setImage(byte[] value){image = value;}

    public Obra toObra()
    {
        return new Obra(null, name, artistId.intValue());
    }

}
