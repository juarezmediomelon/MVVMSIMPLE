package com.mediomelon.mvvmbasic.api;

import com.mediomelon.mvvmbasic.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private ServiceClient() {
        //constructor vacio
    }
    //User
    public static IUserService createUserService() {
        return getRetrofitClient().create(IUserService.class);
    }
    //Hero
    public static IHeroeService createHeroeService() {
        return getRetrofitClient().create(IHeroeService.class);
    }

    public static Retrofit getRetrofitClient() {

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttp())
                .build();
    }

    private static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(provideLoggingInterceptor())
                .build();
    }

    private static Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }
}
