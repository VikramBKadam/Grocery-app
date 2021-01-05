package com.example.vikramapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartModel {
    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "_id")
    public String _id;

    @ColumnInfo(name = "catId")
    public float catId;

    @ColumnInfo(name = "subId")
    public float subId;

    @ColumnInfo(name = "productName")
    public String productName;

    @ColumnInfo(name = "image")
    public String image;


    @ColumnInfo(name = "price")
    public float price;

    @ColumnInfo(name = "mrp")
    public float mrp;



    @PrimaryKey(autoGenerate = true)
    public int uuid;

    @ColumnInfo(name="Quantity")
    public int quantity;
}
