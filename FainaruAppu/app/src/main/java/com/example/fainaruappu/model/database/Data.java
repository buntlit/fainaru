package com.example.fainaruappu.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_data")
public class Data {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String url;
    public int clicks;
}
