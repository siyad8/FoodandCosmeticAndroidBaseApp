package com.mzitow.foodsandcosmeticjungle.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;

@Entity(tableName = "Image")
public class ImageEntity {

//
//
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    private int id;
//
//
//    public ImageEntity(ArrayList imageSources) {
//        this.image = imageSources;
//    }
//
//    public ImageEntity() {
//
//    }
//
//    public ArrayList<String> getImage() {
//        return image;
//    }
//
//    public void setImage(ArrayList<String> image) {
//        this.image = image;
//    }
//
////    @TypeConverters
////    @ColumnInfo(name = "imageList")
////    private ArrayList<String> image;
//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
