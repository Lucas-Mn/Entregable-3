package com.example.entregable3.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.entregable3.model.dao.ArtistTableDAO;
import com.example.entregable3.model.dao.ObraTableDAO;
import com.example.entregable3.model.pojo.ArtistTable;
import com.example.entregable3.model.pojo.ObraTable;

@Database(entities = {ArtistTable.class, ObraTable.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract ArtistTableDAO getArtistDAO();
    public abstract ObraTableDAO getObraTableDAO();

}
