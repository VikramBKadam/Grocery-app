package com.example.vikramapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vikramapp.R;
import com.example.vikramapp.app.Config;
import com.example.vikramapp.models.CartDao;
import com.example.vikramapp.models.CartDatabase;
import com.example.vikramapp.models.CartModel;
import com.example.vikramapp.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ProductActivity extends AppCompatActivity {
    Toolbar toolbar;
    Products product;
    CartModel product1;

    TextView product_name, product_description, product_mrp, product_price;
    ImageView product_image;
    Button addToCart;
    public AsyncTask<Products, Void, Products> insertTask;
   // private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        if (getIntent() != null) {
            Log.d("abc", "intent received");
            product = (Products) getIntent().getSerializableExtra("product");

        }
        init();

    }

    private void init() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(product.getProductName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addToCart = findViewById(R.id.add_to_cart);


        product_name = findViewById(R.id.text_view_product_name);
        product_description = findViewById(R.id.text_view_product_description);
        product_mrp = findViewById(R.id.text_view_mrp);
        product_price = findViewById(R.id.text_view_price);
        product_image = findViewById(R.id.image_view);
        product_name.setText(product.getProductName());
        product_description.setText(product.getDescription());
        product_mrp.setText("MRP: " + String.valueOf(product.getMrp()));
        product_price.setText("Price: " + String.valueOf(product.getPrice()));
        Picasso.with(this)
                .load(Config.IMAGE_URL + product.getImage())
                .fit()
                .error(R.drawable.ic_launcher_background)
                .into(product_image);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              insertTask=new InsertProductTask();
             insertTask.execute( product);





            }
        }
        );


    }
    class InsertProductTask extends AsyncTask<Products, Void, Products> {
        CartDao dao = CartDatabase.getInstance(getApplication()).cartDao();



       Boolean IsItemInCart(Products product){
           Products cursor = dao.getProduct(product.productName);
           return cursor != null;


       }

       void updateItemQty(Products product){
           product.pCount=product.pCount+1;

           dao.updateProduct(product.pCount,product.productName);
       }

        Products addItemToCart(Products product){
            if(!IsItemInCart(product)){
                // add the item in the table
                product.pCount=1;
                dao.insertAll(product);
            }else{
                // call update method to increment the quantity

                updateItemQty(product);
            }
            return product;
        }

        @Override
        protected Products doInBackground(Products... products) {
            Products product = products[0];


             addItemToCart(product);
             return product;
        }



    }




}