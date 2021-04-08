package com.example.antsecommerce1.Interface;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //public static String  BASE_URL =  "http://113.11.231.20:8070/";
    //public static String  BASE_URL =  "http://219.91.165.164:8070/";
    public static String  BASE_URL =  "http://mcommerce.alpha-numero.com:8070";
    private static Retrofit retrofit = null;



    public static Retrofit getClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(150, TimeUnit.SECONDS)
                .writeTimeout(150, TimeUnit.SECONDS)
                .build();

            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
            return retrofit;
        }

}
