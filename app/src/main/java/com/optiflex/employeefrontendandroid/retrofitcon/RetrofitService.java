package com.optiflex.employeefrontendandroid.retrofitcon;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.136:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T createService(Class<T> serviceClass) {
        RequestBody d;
        return retrofit.create(serviceClass);
    }
}
