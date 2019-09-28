package com.mediomelon.mvvmbasic.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mediomelon.mvvmbasic.api.ServiceClient;
import com.mediomelon.mvvmbasic.model.Hero;
import com.mediomelon.mvvmbasic.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroeViewModel extends ViewModel {

    private MutableLiveData<List<Hero>> heroesList;

    public LiveData<List<Hero>> getHeroes(){
        if (heroesList == null){
            heroesList = new MutableLiveData<List<Hero>>();
            loadHeroes();
        }
        return heroesList;
    }

    private void loadHeroes(){

        Call<List<Hero>> callHero = ServiceClient.createHeroeService().getHeroes();
        callHero.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroesList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.e("error", " "+t);
            }
        });
    }
}
