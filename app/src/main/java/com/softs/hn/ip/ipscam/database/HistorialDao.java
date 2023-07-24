package com.softs.hn.ip.ipscam.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistorialDao {

    @Insert
    void insert(Historial nuevo);

    @Update
    void update (Historial actualizar);

    @Delete
    void delete(Historial eliminar);

    @Query("DELETE FROM historial")
    void deleteAll();

    @Query("select * from historial order by id")
    LiveData<List<Historial>>getHistorial();
}
