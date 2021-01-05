package com.example.vikramapp.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vikramapp.R;
import com.example.vikramapp.app.Config;

import com.example.vikramapp.models.CartDatabase;
import com.example.vikramapp.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Products> mProducts = new ArrayList<>();
    View itemView;

   ;


    public CartRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item_in_cart, parent, false);
        return new CartRecyclerViewAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Products product = mProducts.get(position);

        holder.textProductName.setText(product.getProductName());
        holder.pCount.setText(String.valueOf(product.pCount));
        holder.textViewPrice.setText(String.format("Price: %s", String.valueOf(product.price)));
        holder.textViewMrp.setText(String.format("Mrp: %s", String.valueOf(product.mrp)));

        Picasso.with(mContext)
                .load(Config.IMAGE_URL + product.getImage())
                //.fit()
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<Products, Void, Void> deleteTask =new DeleteProductTask();

                deleteTask.execute(product);
                notifyItemRemoved(position);

               // notifyDataSetChanged();
               // Log.d("abc",String.valueOf(mProducts.size()));







            }
        });


        holder.AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.pCount=product.pCount+1;
                Log.d("abc",String.valueOf(product.pCount));
                notifyDataSetChanged();

            }
        });
        holder.RemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(product.pCount>1){

                product.pCount=product.pCount-1;
                Log.d("abc",String.valueOf(product.pCount));
                notifyDataSetChanged();
                }
                else {
                     AsyncTask<Products, Void, Void> deleteTask =new DeleteProductTask();

                    deleteTask.execute(product);
                    holder.notifyAll();

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setData(List<Products> list) {
        mProducts= list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textProductName, pCount ,textViewPrice,textViewMrp;
        ImageButton delete,AddProduct, RemoveProduct;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textProductName=itemView.findViewById(R.id.text_view_product_name);
            textViewPrice=itemView.findViewById(R.id.text_view_price);
            textViewMrp=itemView.findViewById(R.id.text_view_mrp);

            pCount=itemView.findViewById(R.id.p_count);
            delete=itemView.findViewById(R.id.delete);
            AddProduct=itemView.findViewById(R.id.add_product);
            RemoveProduct=itemView.findViewById(R.id.remove_product);




        }
    }

    private class DeleteProductTask extends AsyncTask<Products, Void, Void> {



        @Override
        protected Void doInBackground(Products... products) {
            Products product = products[0];
            CartDatabase.getInstance(mContext).cartDao().deleteProduct(product.productName);
            return null;
        }
    }

    }


