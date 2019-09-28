package com.mediomelon.mvvmbasic.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mediomelon.mvvmbasic.db.dao.UserDao;
import com.mediomelon.mvvmbasic.model.User;
import com.mediomelon.mvvmbasic.utils.Constants;

@Database(entities = {User.class, User.Address.class, User.Company.class}, version = 1)
public abstract class AlbumDataBase extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile AlbumDataBase INSTANCE;

    public static AlbumDataBase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (AlbumDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumDataBase.class, Constants.NAME_DB)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
