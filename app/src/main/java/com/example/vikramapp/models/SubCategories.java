package com.example.vikramapp.models;

public class SubCategories {
    String subName;
    int subId;

    public SubCategories(String subName, int subId) {
        this.subName = subName;
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}
