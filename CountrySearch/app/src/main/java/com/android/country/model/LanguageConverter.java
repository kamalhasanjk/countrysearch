package com.android.country.model;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;


import java.util.ArrayList;

public class LanguageConverter {

    @TypeConverter
    public String toStringTrans(Launguage[] launguages){
        StringBuilder stringBuilder = new StringBuilder();
        for(Launguage launguage: launguages){
            stringBuilder.append(launguage.getIso639_1()+","+launguage.getIso639_2()+","+launguage.getName()+","+launguage.getNativeName()+"##");
        }
        return stringBuilder.toString();

    }

    @TypeConverter
    public Launguage[] stringToLanguages(String string) {
        String[] curr = string.split("##");
        Launguage[] lanArrayList = new Launguage[curr.length];
        int index = 0;
        for(String str : curr){
            if(str != null && str.length() > 0) {

                String[] data = str.split(",");
                Log.d("stringToLanguages",str+" length "+data.length);
                Launguage launguage = new Launguage();
                launguage.setIso639_1(data[0]);
                launguage.setIso639_2(data[1]);
                launguage.setName(data[2]);
                launguage.setNativeName(data[3]);
                lanArrayList[index] = launguage;
                index++;

            }
        }
        return lanArrayList;
    }



    public String toStringTranslation(Launguage[] launguages){
        StringBuilder stringBuilder = new StringBuilder();
        for(Launguage launguage: launguages){
            stringBuilder.append(launguage.getName()+", ");
        }
        return stringBuilder.toString();

    }
}
