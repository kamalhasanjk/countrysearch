package com.android.country.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.android.country.model.Country;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CountryDao {

    @Query("select * from country")
    List<Country> loadAllCountries();

    @Query("select * from country where name like :countryName")
    List<Country> searchCountry(String countryName);


    @Insert(onConflict = REPLACE )
    void insertCountry(Country country);
}
