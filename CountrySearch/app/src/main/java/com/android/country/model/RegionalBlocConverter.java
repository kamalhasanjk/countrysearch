package com.android.country.model;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

public class RegionalBlocConverter {

    @TypeConverter
    public String toStringRegion(RegionalBlocs[] regionalBlocs){
        StringBuilder stringBuilder = new StringBuilder();
        for(RegionalBlocs regionalBloc: regionalBlocs){
            stringBuilder.append(regionalBloc.getAcronym()+","+regionalBloc.getName()+","+StringArrayConverter.toString(regionalBloc.getOtherAcronyms())+","+StringArrayConverter.toString(regionalBloc.getOtherNames())+"##");
        }
        return stringBuilder.toString();

    }

    @TypeConverter
    public RegionalBlocs[] stringToRegionalBlocs(String string) {
        String[] curr = string.split("##");
        RegionalBlocs[] regionalBlocs = new RegionalBlocs[curr.length];
        int index = 0;
        for(String str : curr){
            if(str != null && str.length() > 0) {
                String[] data = str.split(",");
                Log.d("stringToRegionalBlocs",str+" length "+data.length);
                RegionalBlocs  regionalBloc = new RegionalBlocs();
                regionalBloc.setAcronym(data[0]);
                regionalBloc.setName(data[1]);
                regionalBloc.setOtherAcronyms(StringArrayConverter.toArray(data[2]));
                regionalBloc.setOtherNames(StringArrayConverter.toArray(data[3]));
                regionalBlocs[index] = regionalBloc;
                index++;
            }
        }
        return  regionalBlocs;
    }
}
