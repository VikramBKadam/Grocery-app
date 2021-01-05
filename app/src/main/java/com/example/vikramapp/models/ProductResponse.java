package com.example.vikramapp.models;

import java.util.ArrayList;

public class ProductResponse {
    private boolean error;
    private int count;
    private ArrayList<Products> data;

    public ProductResponse(boolean error, int count, ArrayList<Products> data) {
        this.error = error;
        this.count = count;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Products> getData() {
        return data;
    }

    public void setData(ArrayList<Products> data) {
        this.data = data;
    }
}
