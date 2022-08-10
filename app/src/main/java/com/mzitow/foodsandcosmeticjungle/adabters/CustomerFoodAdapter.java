package com.mzitow.foodsandcosmeticjungle.adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mzitow.foodsandcosmeticjungle.PlaceYourOrder;
import com.mzitow.foodsandcosmeticjungle.ProductDiscription;
import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.CartDao;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class CustomerFoodAdapter extends RecyclerView.Adapter<CustomerFoodAdapter.ViewHolder> {

    public FoodProductEntity foodProductEntity;
    public Context fcontext;
   // TextView tilte ,  dicrp, price,  checkoutAmount;
    public int count = 0;
    private ArrayList<Model> mList;

    public CustomerFoodAdapter(List<FoodProductEntity> foodProductEntities, Context c, ArrayList<Model> mlist) {
        this.foodProductEntities = foodProductEntities;
        this.fcontext = c;
        this.mList=mlist;
    }

    List<FoodProductEntity> foodProductEntities;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_product_card_view, parent, false);

        return new CustomerFoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.productName.setText(foodProductEntities.get(position).getProductName());
        holder.productDescription.setText(foodProductEntities.get(position).getProductDescription());
        holder.productPrice.setText(foodProductEntities.get(position).getProductPrice());
       // Glide.with(fcontext).load(mList.get(position).getImageUrl()).into(holder.imgview);

        holder.detailProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(fcontext, ProductDiscription.class);
                intent.putExtra("name", foodProductEntities.get(position).getProductName() );
                intent.putExtra("description", foodProductEntities.get(position).getProductDescription() );
                intent.putExtra("price", foodProductEntities.get(position).getProductPrice() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                fcontext.startActivity(new Intent(intent));

            }
        });





    }

    @Override
    public int getItemCount() {
        return foodProductEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productDescription,productPrice ,checkout, itemCount;
        public ImageView addtocart,detailProducts, imgview;

       // public Button accept,decline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txtProductName);
            productDescription = itemView.findViewById(R.id.txtProductDiscription);
            productPrice = itemView.findViewById(R.id.txtProductPrice);
            detailProducts = itemView.findViewById(R.id.imgmore);
            addtocart = itemView.findViewById(R.id.imgCart);
            itemCount = itemView.findViewById(R.id.item_count);
            checkout = itemView.findViewById(R.id.checkout);
            imgview = itemView.findViewById(R.id.imgProductImage);




        }
    }
}
