package com.mzitow.foodsandcosmeticjungle.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, ProductEntity.class, CartEnity.class, FoodProductEntity.class, WhatsNewEntity.class, DeliveryDataEntity.class, ImageEntity.class },  version = 5)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductsDao productsDao();
    public abstract CartDao cartDao();
    public abstract FoodProductDao foodProductDao();
    public abstract WhatsNewDao whatsNewDao();
    public abstract  DeliveryDao deliveryDao();
    public  abstract ImageDao imageDao();


    private static final String dbName = "user";
    private static UserDatabase userDatabase;

    public static synchronized UserDatabase getUserDatabase(Context context){

        if(userDatabase == null){

            userDatabase = Room.databaseBuilder(context,UserDatabase.class,dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return userDatabase;
    }





}
