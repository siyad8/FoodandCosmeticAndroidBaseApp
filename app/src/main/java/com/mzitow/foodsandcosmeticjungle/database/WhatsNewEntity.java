package com.mzitow.foodsandcosmeticjungle.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "whats_new")
public class WhatsNewEntity {


    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "productName")
    String productName;

    @ColumnInfo(name = "productPrice")
    String productPrice;

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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @ColumnInfo(name = "productDescription")
    String productDescription;

}
