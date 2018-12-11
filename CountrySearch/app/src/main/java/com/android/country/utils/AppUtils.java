package com.android.country.utils;


import com.android.country.model.Country;

import java.util.List;

public class AppUtils {

    private  List<Country> countries;
    private static AppUtils appUtils;

    private AppUtils(){

    }
    public static AppUtils getInstance(){
        if(appUtils == null) appUtils = new AppUtils();
        return appUtils;
    }

    public  List<Country> getCountries() {
        return countries;
    }

    public  void setCountries(List<Country> countries) {
        this.countries = countries;
    }


}
