package com.mzitow.foodsandcosmeticjungle.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodProductDao {

    @Insert
    void addFoodProduct(FoodProductEntity foodProductEntity);

    @Query("SELECT * from  food_Products")
    List<FoodProductEntity> getAllProductsList();

    @Delete
    public void deletefoodproduct (FoodProductEntity foodProductEntity);


}
