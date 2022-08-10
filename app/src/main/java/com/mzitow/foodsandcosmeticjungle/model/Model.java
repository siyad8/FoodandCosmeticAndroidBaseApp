package com.mzitow.foodsandcosmeticjungle.model;

import com.mzitow.foodsandcosmeticjungle.Upload;

public class Model extends Upload {
    private  String imageUrl;


    public Model() { }

    public Model(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
