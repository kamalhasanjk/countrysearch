package com.android.country.model;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;



public class CurrencyConverter {

    @TypeConverter
    public String currencyToString(Currency[] currencies){
        StringBuilder stringBuilder = new StringBuilder();
        for(Currency currency: currencies){
            stringBuilder.append(currency.getCode()+","+currency.getName()+","+currency.getSymbol()+"##");
        }
        return stringBuilder.toString();
    }
    @TypeConverter
    public Currency[] stringToCurrencies(String string){
        String[] curr = string.split("##");
        Currency[] currencyList = new Currency[curr.length];
        int index = 0;
        for(String str : curr){
            if(str != null && str.length() > 0) {
                String[] data = str.split(",");
                Log.d("stringToCurrencies",str+" length "+data.length);
                Currency currency = new Currency();
                currency.setCode(data[0]);
                currency.setName(data[1]);
                currency.setSymbol(data[2]);
                currencyList[index] = currency;
                index++;
            }
        }
        return currencyList;
    }
}
