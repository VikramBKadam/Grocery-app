package com.example.vikramapp.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CartDao {
    @Insert
    List<Long> insertAll(Products... product);

    @Query("SELECT * FROM Products")
    List<Products> getAllProducts();

    @Query("SELECT SUM(pCount) FROM Products")
    int getTotalCount();




    @Query("SELECT * FROM Products WHERE productName = :ProductName")
    Products getProduct(String ProductName);

    @Query("UPDATE Products SET pCount=:p_count WHERE productName = :ProductName ")
    void updateProduct(int p_count,String ProductName);


    @Query("DELETE  FROM Products  WHERE productName = :ProductName")
    void deleteProduct(String ProductName);


    @Query("DELETE FROM Products")
    void deleteAllProducts();
}
