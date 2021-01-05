package com.example.vikramapp.app;

import android.util.Log;

import static com.example.vikramapp.app.Config.BASE_URL;

public class EndPoints {
    public static final String URL_All_CATEGORIES ="category";
    public static final String URL_SUB_CATEGORIES ="subcategory";
    public static final String URL_PRODUCTS ="products/sub/";

    public static String getAllCategories(){
        String url= Config.BASE_URL+URL_All_CATEGORIES;

        return url;
    }

    public static String getUrlSubCategories(){
        String sub_cat_url= BASE_URL+URL_SUB_CATEGORIES;

        return sub_cat_url;
    }
    public  static String getURLProducts(){
        String product_url= BASE_URL+ URL_PRODUCTS;

        return  product_url;
    }
}
