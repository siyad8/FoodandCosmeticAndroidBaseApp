package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "delivery_pick_info")
public class DeliveryDataEntity {


    @PrimaryKey(autoGenerate = true)
    Integer id;


    @ColumnInfo(name = "name")
    String name;


    @ColumnInfo(name = "location")
    String location;


    @ColumnInfo(name = "phone")
    String phoneNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
