package com.example.entregable3.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.entregable3.model.pojo.ObraTable;

import java.util.List;

@Dao
public interface ObraTableDAO {

    @Insert
    void insert(ObraTable obra);

    @Update
    void update(ObraTable obra);

    @Delete
    void delete(ObraTable obra);

    @Query("SELECT * FROM obras")
    List<ObraTable> getObras();

    @Query("SELECT * FROM obras WHERE name = :name")
    ObraTable getObraByName(String name);

    @Query("DELETE FROM obras")
    void clearTable();
    
}
