package com.example.lobleradam.touristapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(
        entities = {MonumentItem.class},
        version = 1
)

@TypeConverters(value = {MonumentItem.Category.class})

//abstract class representing a database

public abstract class MonumentItemDatabase extends RoomDatabase {
    public abstract MonumentItemDataAccess MonumentItemDataAccess();
}