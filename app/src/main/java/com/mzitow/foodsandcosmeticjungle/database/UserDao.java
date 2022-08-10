package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mzitow.foodsandcosmeticjungle.database.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where name=(:name) and password=(:password)")
    UserEntity login (String name, String password);

}
