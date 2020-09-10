package com.practice.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO
{
    @Insert
    public void addUser(UserEntity user);

    @Query("Select * from user Where user_email=(:email) and user_password=(:password)")
    UserEntity Login(String email, String password);


}
