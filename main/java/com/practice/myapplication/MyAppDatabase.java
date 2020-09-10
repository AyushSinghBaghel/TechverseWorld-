package com.practice.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    private static final String dbname= "user";
    private static MyAppDatabase myAppDatabase;

    public static synchronized MyAppDatabase getMyAppDatabase(Context context){

        if(myAppDatabase == null){
            myAppDatabase = Room.databaseBuilder(context, MyAppDatabase.class,dbname)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return myAppDatabase;
    }

    public abstract UserDAO userDAO();


    


}
