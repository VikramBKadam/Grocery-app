package com.example.vikramapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vikramapp.R;
import com.example.vikramapp.adapters.MyRecyclerViewAdapter1;
import com.example.vikramapp.adapters.MyRecyclerViewAdapter2;
import com.example.vikramapp.app.EndPoints;
import com.example.vikramapp.models.All_Categories;
import com.example.vikramapp.models.ProductResponse;
import com.example.vikramapp.models.Products;
import com.example.vikramapp.models.SubCategoryRespose;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class SubCategoryFragment extends Fragment {
    private static final String SUBCATEGORY_ID = "sub_id";
    RecyclerView recyclerView;
    MyRecyclerViewAdapter2 adapter2;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Products> mProducts = new ArrayList<>();

    private String getSubcategoryId;


    public SubCategoryFragment() {
        // Required empty public constructor
    }


    public static SubCategoryFragment newInstance(String param1) {
        SubCategoryFragment fragment = new SubCategoryFragment();
        Bundle args = new Bundle();
        args.putString(SUBCATEGORY_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            getSubcategoryId = getArguments().getString(SUBCATEGORY_ID);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        getData();
        recyclerView = view.findViewById(R.id.recycler_view_2);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter2 = new MyRecyclerViewAdapter2(getActivity());
        recyclerView.setAdapter(adapter2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void getData() {

        Log.d("abc", EndPoints.getURLProducts() + getSubcategoryId);
        StringRequest stringRequest = new StringRequest
                (Request.Method.GET,
                        EndPoints.getURLProducts() + getSubcategoryId,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new GsonBuilder().create();
                ProductResponse productResponse = gson.fromJson(response, ProductResponse.class);
                adapter2.setData(productResponse.getData());
                Log.d("abc",EndPoints.getURLProducts() + getSubcategoryId);
                Log.d("abc", "Responded subcategory product");
                mProducts.addAll(productResponse.getData());
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Log.d("abc", "error" + error.getMessage());

                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);

    }
}