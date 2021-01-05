package com.example.vikramapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vikramapp.R;
import com.example.vikramapp.adapters.CartRecyclerViewAdapter;
import com.example.vikramapp.models.CartDatabase;
import com.example.vikramapp.models.Products;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView textViewTotalValue,textViewDiscountValue,textViewPayValue;
    Toolbar toolbar;
    RecyclerView recyclerView;
    CartRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
    }

    private void init() {
         setToolbar();
         recyclerView =findViewById(R.id.recycler_view_cart);
         adapter= new CartRecyclerViewAdapter(this);
         layoutManager=new LinearLayoutManager(this);
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(adapter);
         fetchFromDatabase();




    }

    private void fetchFromDatabase() {
        AsyncTask<Void, Void, List<Products>> retrieveTask = new RetrieveProductsTask();
        retrieveTask.execute();
    }

    private void setToolbar() {
        toolbar =findViewById(R.id.tool_bar);
        toolbar.setTitle("CART");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class RetrieveProductsTask extends AsyncTask<Void, Void, List<Products>> {

        @Override
        protected List<Products> doInBackground(Void... voids) {
            return CartDatabase.getInstance(getApplication()).cartDao().getAllProducts();
        }
        @Override
        protected void onPostExecute(List<Products> products) {

            adapter.setData(products);
            double pay=0;
            double total=0;

           for(Products p:products){
               pay=pay+p.price;
               total=total+p.mrp;
           }
           double discount=total-pay;

            textViewTotalValue=findViewById(R.id.textView_total_value);
           textViewTotalValue.setText(String.valueOf(total));

            textViewDiscountValue=findViewById(R.id.textView_discount_value);
            textViewDiscountValue.setText(String.valueOf(discount));

            textViewPayValue=findViewById(R.id.textView_total_pay_value);
            textViewPayValue.setText(String.valueOf(pay));



        }
    }
}

