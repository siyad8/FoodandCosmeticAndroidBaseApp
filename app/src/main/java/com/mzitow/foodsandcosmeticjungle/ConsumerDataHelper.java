package com.mzitow.foodsandcosmeticjungle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConsumerDataHelper  extends SQLiteOpenHelper {
    public ConsumerDataHelper( Context context) {
        super(context, "consumerDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase ConDB) {
        ConDB.execSQL("create Table consumerdetail(name TEXT primary key, contact TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase ConDB , int i, int i1) {
        ConDB.execSQL("drop Table if exists consumerDetails");

    }
}
