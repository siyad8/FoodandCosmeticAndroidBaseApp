package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert
    void addproduct(ProductEntity productEntity);



    @Query("SELECT * from  Products")
    List<ProductEntity> getAllProductsList();


    @Delete
     void deleteproduct (ProductEntity productEntity);













}
