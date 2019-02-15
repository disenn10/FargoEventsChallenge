package com.myriadandroidchallenge.fargoevents;

import com.myriadandroidchallenge.fargoevents.Interfaces.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by disen on 2/6/2019.
 */

public class RetrofitClient {
    private static final String BASE_URL = "https://challenge.myriadapps.com/api/v1/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
