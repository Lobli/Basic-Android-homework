package com.example.lobleradam.touristapp.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MonumentItemDataAccess {
    @Query("SELECT * FROM monumentItem")
    List<MonumentItem> getAll();

    @Insert
    long insert(MonumentItem monumentItem);

    @Update
    void update(MonumentItem monumentItem);

    @Delete
    void deleteItem(MonumentItem monumentItem);
}