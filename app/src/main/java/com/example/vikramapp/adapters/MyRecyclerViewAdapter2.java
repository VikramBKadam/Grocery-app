package com.example.vikramapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vikramapp.R;
import com.example.vikramapp.activities.ProductActivity;
import com.example.vikramapp.app.Config;
import com.example.vikramapp.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.MyViewHolder> {
    Context mContext;
    ArrayList<Products> mProducts = new ArrayList<>();

    Context item_view_context;


    public MyRecyclerViewAdapter2(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products product = mProducts.get(position);
        holder.textView_name.setText(product.getProductName());


        holder.textView_mrp.setText("MRP:  "+String.valueOf(product.getMrp()));


        holder.textView_price.setText("Price: "+ String.valueOf(product.getPrice()));

        Picasso.with(mContext)
                .load(Config.IMAGE_URL + product.getImage())
                //.fit()
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setData(ArrayList<Products> list) {
        mProducts = list;
        notifyDataSetChanged();
    }



    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textView_name, textView_description,textView_price, textView_mrp;
        ImageView imageView;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.text_view_product_name);
            imageView = itemView.findViewById(R.id.image_view);
            //textView_description=itemView.findViewById(R.id.text_view_product_description);
            textView_mrp= itemView.findViewById(R.id.text_view_mrp);
            textView_price = itemView.findViewById(R.id.text_view_price);
            item_view_context=itemView.getContext();
            itemView.isClickable();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
                   Products product = mProducts.get(clickPosition);
                    Intent intent =new Intent(item_view_context, ProductActivity.class);
                    intent.putExtra("product",product);
                  // Log.d("abc",product.getProductName()+"clicked");
                    item_view_context.startActivity(intent);
                }
            });
        }

    }
}
