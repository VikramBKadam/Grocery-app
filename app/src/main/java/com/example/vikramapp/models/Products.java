package com.example.vikramapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Products implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uuid;

    @ColumnInfo(name="pCount")
    public int pCount=0;

    @ColumnInfo(name = "productName")
    public String productName;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "_id")
    public String _id;

    @ColumnInfo(name = "catId")
    public float catId;

    @ColumnInfo(name = "subId")
    public float subId;

    @ColumnInfo(name = "price")
    public float price;

    @ColumnInfo(name = "mrp")
    public float mrp;

    @ColumnInfo(name = "image")
    public String image;



    @ColumnInfo(name="Quantity")
    public float quantity;



    private boolean status;
    private float position;
    private String created;
    private String unit;
    private float __v;

    public Products(float quantity, String description, boolean status, float position, String created, String _id, float catId, float subId, String productName, String image, String unit, float price, float mrp, float __v) {
        this.quantity = quantity;
        this.description = description;
        this.status = status;
        this.position = position;
        this.created = created;
        this._id = _id;
        this.catId = catId;
        this.subId = subId;
        this.productName = productName;
        this.image = image;
        this.unit = unit;
        this.price = price;
        this.mrp = mrp;
        this.__v = __v;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public float getCatId() {
        return catId;
    }

    public void setCatId(float catId) {
        this.catId = catId;
    }

    public float getSubId() {
        return subId;
    }

    public void setSubId(float subId) {
        this.subId = subId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public float get__v() {
        return __v;
    }

    public void set__v(float __v) {
        this.__v = __v;
    }
}




