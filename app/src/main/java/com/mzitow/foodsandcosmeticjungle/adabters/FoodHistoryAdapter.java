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

import com.mzitow.foodsandcosmeticjungle.ProductCosmeticDiscription;
import com.mzitow.foodsandcosmeticjungle.ProductHistory;
import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class FoodHistoryAdapter extends RecyclerView.Adapter<FoodHistoryAdapter.viewholder>{

    Context cont;
    List<FoodProductEntity> foodProductEntityList;
    private ArrayList<Model> mList;

    public FoodHistoryAdapter() {

    }

    public FoodHistoryAdapter(Context cont, List<FoodProductEntity> foodProductEntityList, OnDeletedListener onDeletedListener, ArrayList<Model> mList) {
        //this.cont = cont;
        this.foodProductEntityList = foodProductEntityList;
        this.onDeletedListener = onDeletedListener;
        this.mList= mList;
    }

    public FoodHistoryAdapter(Context cont, List<FoodProductEntity> foodProductEntityList, ProductHistory productHistory, ArrayList<Model> list) {

        this.cont = cont;
        this.foodProductEntityList = foodProductEntityList;
        this.onDeletedListener = onDeletedListener;
        this.mList= mList;

    }



    public  interface OnDeletedListener{
        void OnDeletedlistener(FoodProductEntity foodProductEntity);

    }
    public  OnDeletedListener onDeletedListener;

   // public FoodProductEntity.OnDeleteListener onDeleteListener;



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_card,parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.productNameHis.setText(foodProductEntityList.get(position).getProductName());
        holder.productDescriptionHis.setText(foodProductEntityList.get(position).getProductDescription());
        holder.productPricehis.setText(foodProductEntityList.get(position).getProductPrice());

        holder.detls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cont, ProductCosmeticDiscription.class);
                intent.putExtra("name", foodProductEntityList.get(position).getProductName() );
                intent.putExtra("description", foodProductEntityList.get(position).getProductDescription() );
                intent.putExtra("price", foodProductEntityList.get(position).getProductPrice() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cont.startActivity(new Intent(intent));

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  ProductEntity productEntity = new ProductEntity();
               // ProductHistory productHistory = new ProductHistory();

                FoodProductEntity foodProductEntity = new FoodProductEntity();
                UserDatabase userDatabase = UserDatabase.getUserDatabase(view.getContext());


                int ID=  foodProductEntityList.get(holder.getAdapterPosition()).getId();
//
                foodProductEntity.setId(ID);
//
                //userDatabase.productsDao().deleteproduct(productEntity);

                if (onDeletedListener != null){

                    onDeletedListener.OnDeletedlistener(foodProductEntity);
                }


                // productHistory.getSupportFragmentManager().beginTransaction().replace(R.id.,)











            }
        });


    }

    @Override
    public int getItemCount() {
        return foodProductEntityList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        public TextView productNameHis, productDescriptionHis,detls, productPricehis , delete, cosDetailProducts;
        public Button accept;
        public ImageView img;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            productDescriptionHis = itemView.findViewById(R.id.fooditem_short_desc);
            productNameHis = itemView.findViewById(R.id.foodproduct_item_name);
            productPricehis = itemView.findViewById(R.id.foodproduct_item_price);
            delete = itemView.findViewById(R.id.tv_delete_food_product);
            detls = itemView.findViewById(R.id.tv_details_food_product);
            img = itemView.findViewById(R.id.product_thumb);



        }
    }
}
