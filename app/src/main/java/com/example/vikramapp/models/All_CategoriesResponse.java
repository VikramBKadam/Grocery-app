package com.example.vikramapp.models;

import java.util.ArrayList;

public class All_CategoriesResponse {
    private boolean error;
    private int count;
    private ArrayList<All_Categories> data;

    public All_CategoriesResponse(boolean error, int count, ArrayList<All_Categories> data) {
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

    public ArrayList<All_Categories> getData() {
        return data;
    }

    public void setData(ArrayList<All_Categories> data) {
        this.data = data;
    }
}
