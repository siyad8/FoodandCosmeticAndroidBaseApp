package com.mzitow.foodsandcosmeticjungle.adabters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.HairCare;
import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.MakeUp;
import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.Perfume;

public class CosmeticTabLayOutAdapter extends FragmentStateAdapter {
    public CosmeticTabLayOutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0 :
               return new Perfume();
           case 1 :
               return new HairCare();
           case 2 :
               return new MakeUp();
           default:
               return new Perfume();
       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
