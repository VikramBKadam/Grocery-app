package com.example.vikramapp.models;

import java.util.ArrayList;

public class SubCategoryRespose {
    private boolean error;
    private int count;
    private ArrayList<SubCategories> data;

    public SubCategoryRespose(boolean error, int count, ArrayList<SubCategories> data) {
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

    public ArrayList<SubCategories> getData() {
        return data;
    }

    public void setData(ArrayList<SubCategories> data) {
        this.data = data;
    }
}
