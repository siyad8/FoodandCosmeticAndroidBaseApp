package com.mzitow.foodsandcosmeticjungle.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "carts")
public class CartEnity {

    @PrimaryKey(autoGenerate = true)
    Integer id;


    @ColumnInfo(name = "cartName")
    String cartName;


    @ColumnInfo(name = "cartDescription")
    String cartDescription;


    @ColumnInfo(name = "cartPrice")
    String cartPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartDescription() {
        return cartDescription;
    }

    public void setCartDescription(String cartDescription) {
        this.cartDescription = cartDescription;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }
}
