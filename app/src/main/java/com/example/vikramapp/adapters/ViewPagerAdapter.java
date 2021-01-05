package com.example.vikramapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.vikramapp.R;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    Context mContext;
     int[] images;
     LayoutInflater mLayoutInflater;
     ImageView imageView;

    public ViewPagerAdapter(Context mContext, int[] images) {
        this.mContext = mContext;
        this.images = images;
        mLayoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.sliding_images,container,false);
        imageView =itemView.findViewById(R.id.image_view);
        imageView.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemView);
      return  itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
