package com.example.vikramapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vikramapp.R;
import com.example.vikramapp.adapters.MyFragmentAdapter;
import com.example.vikramapp.adapters.MyRecyclerViewAdapter1;
import com.example.vikramapp.adapters.MyRecyclerViewAdapter2;
import com.example.vikramapp.app.EndPoints;
import com.example.vikramapp.fragments.SubCategoryFragment;
import com.example.vikramapp.models.All_CategoriesResponse;
import com.example.vikramapp.models.CartDatabase;
import com.example.vikramapp.models.ProductResponse;
import com.example.vikramapp.models.Products;
import com.example.vikramapp.models.SubCategories;
import com.example.vikramapp.models.SubCategoryRespose;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {
    ArrayList<SubCategories> subCategories=new ArrayList<>();
    ArrayList<Products> mProducts = new ArrayList<>();
    Toolbar toolbar;
    TextView textView;
    TextView textViewCartCount;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyRecyclerViewAdapter2 adapter2;

    MyFragmentAdapter adapter;

    String ID;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_cart,menu);

        MenuItem item =menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item,R.layout.menu_cart_layout);
        View view =MenuItemCompat.getActionView(item);

        textViewCartCount=view.findViewById(R.id.text_view_cart_count);

        updateUI();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubCategoryActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void updateUI() {
         class GetTotalCountTask extends AsyncTask<Void, Void, Integer> {

            @Override
            protected Integer doInBackground(Void... voids) {
                return CartDatabase.getInstance(getApplication()).cartDao().getTotalCount();
            }

             @Override
             protected void onPostExecute(Integer c) {
                 super.onPostExecute(c);
                 if(c ==0){
                     textViewCartCount.setVisibility(View.INVISIBLE);
                 }else{
                     textViewCartCount.setVisibility(View.VISIBLE);
                     textViewCartCount.setText(String.valueOf(c));
                     Log.d("abc","count "+String.valueOf(c));
                 }


             }
         }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        textView=findViewById(R.id.text_view);

        ID=getIntent().getStringExtra("id");

        getData();
       init();



    }


    private void init() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("Sub Categories");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        adapter =new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    private void getData() {

        Log.d("abc", "inside sub category getDATA");
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET,
                        EndPoints.getUrlSubCategories()+"/"+ID,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Gson gson = new GsonBuilder().create();
                                SubCategoryRespose subCategoryRespose = gson.fromJson(response, SubCategoryRespose.class);
                                adapter.setData(subCategoryRespose.getData());
                              //  Log.d("abcd",EndPoints.getUrlSubCategories()+"/"+ID);
                                Log.d("abc", "Responded subcategory");
                                //optional
                                subCategories.addAll(subCategoryRespose.getData());
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                                Log.d("abc", "error" + error.getMessage());

                            }
                        }
                );
        Volley.newRequestQueue(this).add(stringRequest);

    }


}