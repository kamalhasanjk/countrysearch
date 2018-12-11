package com.android.country.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.android.country.model.Country;

@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract CountryDao countryModel();

    static final String DATABASE_NAME = "country.db";

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        if (INSTANCE != null) INSTANCE.close();
        INSTANCE = null;
    }
}