package com.android.country.rest;

import com.android.country.model.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

     static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

   // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
  //  interceptor.Level = HttpLoggingInterceptor.Level.BODY;
    static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


    private static <T> T builder(Class<T> endpoint) {

        return new Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

                .create(endpoint);
    }

    public static Countries countries() {
        return builder(Countries.class);
    }


}
