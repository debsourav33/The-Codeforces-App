package com.example.codeforcesapp.networking.common;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    static Retrofit retrofit;
    static CodeforcesAPI cfAPI;
    public static final String BaseUrl= "https://codeforces.com/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static CodeforcesAPI getCodeforcesAPIInstance(){
        retrofit= getRetrofitInstance();

        if(cfAPI==null){
            cfAPI= retrofit.create(CodeforcesAPI.class);
        }

        return cfAPI;
    }

}
