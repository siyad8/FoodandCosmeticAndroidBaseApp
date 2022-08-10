package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void addproduct(CartEnity cartEnity);

    @Query("SELECT * from  carts")
    List<CartEnity> getAllProductsList();

    @Query("DELETE FROM carts")
    void deleteAll();

}
