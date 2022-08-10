package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WhatsNewDao {

    @Insert
    void addproduct(WhatsNewEntity whatsNewEntity);

    //

    @Query("SELECT * from  Products")
    List<WhatsNewEntity> getAllProductsList();

}
