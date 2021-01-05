package com.example.vikramapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vikramapp.R;
import com.example.vikramapp.adapters.MyRecyclerViewAdapter1;
import com.example.vikramapp.adapters.ViewPagerAdapter;
import com.example.vikramapp.app.EndPoints;
import com.example.vikramapp.models.All_Categories;
import com.example.vikramapp.models.All_CategoriesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager;

public class  MainActivity extends AppCompatActivity {
    ArrayList<All_Categories> mAll_Categories = new ArrayList<>();
    Toolbar toolbar;
    ProgressBar progressBar;
    AutoScrollViewPager viewPager;
    ViewPagerAdapter adapter;
    MyRecyclerViewAdapter1 adapter1;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    int[] images = {R.drawable.p1, R.drawable.p2, R.drawable.p3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        toolbar=findViewById(R.id.tool_bar);
        toolbar.setTitle("My Toolbar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        setViewPager();
        getData();
        setRecyclerView();

    }

    private void getData() {
        Log.d("abc", "inside getDATA");
        Log.d("abc",   EndPoints.getAllCategories());
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET,
                        EndPoints.getAllCategories(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                Gson gson = new GsonBuilder().create();
                                All_CategoriesResponse all_categoriesResponse = gson.fromJson(response, All_CategoriesResponse.class);
                                adapter1.setData(all_categoriesResponse.getData());
                                Log.d("abc", "Responded");
                                //optional
                                mAll_Categories.addAll(all_categoriesResponse.getData());
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressBar.setVisibility(View.GONE);
                            //    Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                Log.d("abc", "error" + error.getMessage());

                            }
                        }
                );
        Volley.newRequestQueue(this).add(stringRequest);

    }

    void setRecyclerView() {
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view_1);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter1 = new MyRecyclerViewAdapter1(this);
        recyclerView.setAdapter(adapter1);

        // recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    void setViewPager() {
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(adapter);
        viewPager.setInterval(2000);
        viewPager.setDirection(AutoScrollViewPager.Direction.RIGHT);
        viewPager.setCycle(true);
        // viewPager.setBorderAnimation(true);
        // viewPager.setSlideBorderMode(AutoScrollViewPager.SlideBorderMode.TO_PARENT);
        viewPager.startAutoScroll();

    }


}