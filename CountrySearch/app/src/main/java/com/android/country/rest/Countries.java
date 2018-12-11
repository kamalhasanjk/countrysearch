package com.android.country.rest;



import com.android.country.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Countries {

    @GET("/rest/v2/name/{name}")
    Call<List<Country>> findByName(@Path("name") String name);

    @GET("/rest/v2/all")
    Call<List<Country>> findAll();

}

