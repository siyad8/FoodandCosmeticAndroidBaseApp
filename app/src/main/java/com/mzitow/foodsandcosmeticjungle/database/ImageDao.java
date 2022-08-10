package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {
    @Insert
    void insert(ImageEntity... imageEntities);
    @Query("SELECT * FROM Image")
    List<ImageEntity> getAllImage();

    @Query("SELECT * FROM Image where image_id = :imageId")
    List<ImageEntity> getImageByImageId(int imageId);
}
