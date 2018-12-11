package com.android.country.model;

import android.arch.persistence.room.TypeConverter;

import java.util.Arrays;
import java.util.Date;

public class StringArrayConverter {

    @TypeConverter
    public static String toString(String[] array) {
        return  Arrays.toString(array);
    }

    @TypeConverter
    public static String[] toArray(String str) {
        return str == null ? null : str.split(",");
    }
}
