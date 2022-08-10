package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.mzitow.foodsandcosmeticjungle.adabters.CosmeticTabLayOutAdapter;

public class CosmeticsTabLayOut extends AppCompatActivity {
    TabLayout tabCosmetics;
    ViewPager2 viewPager2Cos;
    CosmeticTabLayOutAdapter cosmeticTabLayOutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetics_tab_lay_out);

        tabCosmetics = findViewById(R.id.TablayoutCosmetics);
        viewPager2Cos = findViewById(R.id.ViewpagerCosmetics);
        cosmeticTabLayOutAdapter = new CosmeticTabLayOutAdapter(this);

        viewPager2Cos.setAdapter(cosmeticTabLayOutAdapter);

        tabCosmetics.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2Cos.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2Cos.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabCosmetics.getTabAt(position).select();
            }
        });

    }
}