package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeliveryDao {

    @Insert
    void addDeliveryInfo(DeliveryDataEntity deliveryDataEntity);

    @Query("SELECT * from  delivery_pick_info")
    List<DeliveryDataEntity> getAllDeliveryList();

    @Query("DELETE FROM delivery_pick_info")
    void deleteAll();


}
