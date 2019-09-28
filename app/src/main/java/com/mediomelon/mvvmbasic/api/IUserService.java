package com.mediomelon.mvvmbasic.api;

import com.mediomelon.mvvmbasic.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUserService {
    //https://jsonplaceholder.typicode.com/users
    @GET("users")
    Call<List<User>> getUsers();
}
