package com.example.entregable3.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.entregable3.model.pojo.ArtistTable;

import java.util.List;

@Dao
public interface ArtistTableDAO {

    @Insert
    void insert(ArtistTable artist);

    @Update
    void update(ArtistTable artist);

    @Delete
    void delete(ArtistTable artist);

    @Query("SELECT * FROM artists")
    List<ArtistTable> getArtists();

    @Query("SELECT * FROM artists WHERE id = :id")
    ArtistTable getArtistById(Long id);

    @Query("DELETE FROM artists")
    void clearTable();

}
