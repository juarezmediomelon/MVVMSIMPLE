package com.mediomelon.mvvmbasic.api;

import com.mediomelon.mvvmbasic.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IHeroeService {

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
