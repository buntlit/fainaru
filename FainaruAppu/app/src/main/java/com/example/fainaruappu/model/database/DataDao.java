package com.example.fainaruappu.model.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DataDao {

    @Query("SELECT * FROM table_data")
    Single<List<Data>> getAll();

    @Query("SELECT * FROM table_data")
    List<Data> getList();

    @Insert
    Single<Long> insert (Data data);

    @Update
    Single<Integer> update(Data data);
}
