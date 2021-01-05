package com.example.vikramapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.vikramapp.fragments.SubCategoryFragment;
import com.example.vikramapp.models.All_Categories;
import com.example.vikramapp.models.Products;
import com.example.vikramapp.models.SubCategories;

import java.util.ArrayList;

public class MyFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<SubCategories> subCategories = new ArrayList<>();


    public MyFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        SubCategories SubCategories = subCategories.get(position);
        return SubCategoryFragment.newInstance(String.valueOf(SubCategories.getSubId()));
    }

    @Override
    public int getCount() {
        return subCategories.size();
    }

    public void setData(ArrayList<SubCategories> list) {
        subCategories = list;
        notifyDataSetChanged();
    }

    public CharSequence getPageTitle(int position) {
        SubCategories SubCategories = subCategories.get(position);

        return SubCategories.getSubName();

    }
}
