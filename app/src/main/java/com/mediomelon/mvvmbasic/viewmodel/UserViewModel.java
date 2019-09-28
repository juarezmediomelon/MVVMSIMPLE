package com.mediomelon.mvvmbasic.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mediomelon.mvvmbasic.api.ServiceClient;
import com.mediomelon.mvvmbasic.db.AlbumDataBase;
import com.mediomelon.mvvmbasic.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private AlbumDataBase albumDataBase;
    private MutableLiveData<List<User>> usersList;
    private String txtdataResource;
    private Context context;
    private List<User> dbUserList;
    private static final String TAG = "UserViewModel";


    //setter context
    public void setContext(Context ctx) {
        this.context = ctx;
        albumDataBase = AlbumDataBase.getDataBase(ctx);
    }

    //getter de texto de origen de recurso
    public String getDataResource() {
        return txtdataResource;
    }

    //get con LiveData para elegir origen de datos
    public LiveData<List<User>> getUsers() {

        //obtener datos de la bd
        dbUserList = albumDataBase.userDao().getUsers();
        Log.e(TAG, "size: " + dbUserList.size());

        //comprobar si bd esta vacio
        if (dbUserList.size() == 0) {
            usersList = new MutableLiveData<List<User>>();
            txtdataResource = "datos desde API";
            loadUsersAPI();
        } else {
            usersList = new MutableLiveData<List<User>>();
            txtdataResource = "datos desde DB";
            loadUsersDb(context);
        }

        return usersList;
    }

    //metodo para cargar usuarios desde API
    private void loadUsersAPI() {

        //Retrofit
        Call<List<User>> callUser = ServiceClient.createUserService().getUsers();
        callUser.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                //añadir a db, la primera vez
                for (User user : response.body()) {
                    albumDataBase.userDao().addUser(user);
                }
                Log.e(TAG, ""+response.code());

                if (response.code() >= 200 && response.code() <= 300)
                        usersList.setValue(response.body());
                else if(response.code() == 400)
                        usersList.setValue(null);
                else if(response.code() == 404)
                        usersList.setValue(null);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                usersList.setValue(null);
                Log.e("Error", t.toString());

            }
        });
    }

    //metodo para cargar datos desde Db
    private void loadUsersDb(Context context) {
        albumDataBase = AlbumDataBase.getDataBase(context);
        usersList = new MutableLiveData<>(albumDataBase.userDao().getUsers());
    }
}
