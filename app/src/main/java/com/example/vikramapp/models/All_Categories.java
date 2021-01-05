package com.example.vikramapp.models;

public class All_Categories {

    int catId;
    String catName;
    String catImage;

    public All_Categories(int catId, String catName, String catImage) {
        this.catId = catId;
        this.catName = catName;
        this.catImage = catImage;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }
}
