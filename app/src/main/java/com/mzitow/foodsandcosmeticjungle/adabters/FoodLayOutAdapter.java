package com.mzitow.foodsandcosmeticjungle.adabters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mzitow.foodsandcosmeticjungle.fragraments.Foods.Bevarages;
import com.mzitow.foodsandcosmeticjungle.fragraments.Foods.Cakes;
import com.mzitow.foodsandcosmeticjungle.fragraments.Foods.Sweet;
import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.HairCare;
import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.MakeUp;
import com.mzitow.foodsandcosmeticjungle.fragraments.cosmetics.Perfume;

public class FoodLayOutAdapter extends FragmentStateAdapter {
    public FoodLayOutAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 :
                return new Bevarages();
            case 0 :
            default:
                return new Sweet();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
