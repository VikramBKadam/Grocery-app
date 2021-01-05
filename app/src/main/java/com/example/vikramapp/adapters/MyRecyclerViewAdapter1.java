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
import com.example.vikramapp.activities.SubCategoryActivity;
import com.example.vikramapp.app.Config;
import com.example.vikramapp.models.All_Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewAdapter1 extends RecyclerView.Adapter<MyRecyclerViewAdapter1.MyViewHolder> {
    Context mContext;
    ArrayList<All_Categories> mAll_Categories = new ArrayList<>();
    All_Categories all_categories;
    Context item_view_context;
    String id;

    public MyRecyclerViewAdapter1(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        All_Categories all_categories = mAll_Categories.get(position);
        holder.textView.setText(all_categories.getCatName());
        Picasso.with(mContext)
        .load(Config.IMAGE_URL + all_categories.getCatImage())
               //.fit()
               .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
       // Log.d("abc",Config.IMAGE_URL + all_categories.getCatImage());
       // holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);




    }

    @Override
    public int getItemCount() {
        return mAll_Categories.size();
    }

    public void setData(ArrayList<All_Categories> list) {
        mAll_Categories = list;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        ImageView imageView;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.category_name);
            imageView = itemView.findViewById(R.id.category_image);
            item_view_context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
                    All_Categories all_categories = mAll_Categories.get(clickPosition);
                    Intent intent =new Intent(item_view_context,SubCategoryActivity.class);

                    id =String.valueOf(all_categories.getCatId());
                    Log.d("abc","catID"+id);
                    intent.putExtra("id",id);
                   // Log.d("abc",id);
                    item_view_context.startActivity(intent);
                }
            });
        }

        }
    }


