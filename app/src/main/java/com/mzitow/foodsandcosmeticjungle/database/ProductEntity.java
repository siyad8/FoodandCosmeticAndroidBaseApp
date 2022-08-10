package com.mzitow.foodsandcosmeticjungle.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Products")
public class ProductEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }





    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @ColumnInfo(name = "productName")
    String productName;

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @ColumnInfo(name = "productPrice")
    String productPrice;

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @ColumnInfo(name = "productDescription")
    String productDescription;

}
