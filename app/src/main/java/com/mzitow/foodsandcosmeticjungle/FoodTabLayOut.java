package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mzitow.foodsandcosmeticjungle.adabters.FoodLayOutAdapter;

public class FoodTabLayOut extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FoodLayOutAdapter foodLayOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tab_lay_out);

        tabLayout = findViewById(R.id.TablayoutFoods);
        viewPager2  = findViewById(R.id.ViewpagerFoods);
        foodLayOutAdapter = new FoodLayOutAdapter(this);

        viewPager2.setAdapter(foodLayOutAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }
}