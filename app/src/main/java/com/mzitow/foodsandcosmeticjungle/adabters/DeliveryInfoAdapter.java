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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mzitow.foodsandcosmeticjungle.MainActivity;
import com.mzitow.foodsandcosmeticjungle.ProductCosmeticDiscription;
import com.mzitow.foodsandcosmeticjungle.ProductHistory;
import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductsDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class DeliveryInfoAdapter extends RecyclerView.Adapter<DeliveryInfoAdapter.ViewHolder> {
  //  private DatabaseReference root = FirebaseDatabase.getInstance().getReference("images");

    public  interface OnDeleteListener{
        void OnDeletelistener(ProductEntity productEntity);

    }

    List<ProductEntity> productsList;
    Context cont;
    List<FoodProductEntity> foodProductEntityList;
    private ArrayList<Model> mList;

    public OnDeleteListener onDeleteListener;




    public DeliveryInfoAdapter(List<ProductEntity> productsList,  OnDeleteListener Listener,  Context cons,  ArrayList<Model> mList) {
        this.productsList = productsList;
        this.cont = cons;
        this.onDeleteListener = Listener;
        //this.foodProductEntityList = foodProductEntityList;
        this.mList= mList;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.productNameHis.setText(productsList.get(position).getProductName());
        holder.productDescriptionHis.setText(productsList.get(position).getProductDescription());
        holder.productPricehis.setText(productsList.get(position).getProductPrice());



//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Model model = dataSnapshot.getValue(Model.class);
//                   mList.add(model);
//                }
//                //addProductAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        });
        //Glide.with(cont).load(mList.get(position).getImageUrl()).into(holder.img);

        holder.detls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cont, ProductCosmeticDiscription.class);
                intent.putExtra("name", productsList.get(position).getProductName() );
                intent.putExtra("description", productsList.get(position).getProductDescription() );
                intent.putExtra("price", productsList.get(position).getProductPrice() );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cont.startActivity(new Intent(intent));

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductEntity productEntity = new ProductEntity();
                ProductHistory productHistory = new ProductHistory();

                UserDatabase userDatabase = UserDatabase.getUserDatabase(view.getContext());


                    int ID=  productsList.get(holder.getAdapterPosition()).getId();
//
                       productEntity.setId(ID);
//
                   //userDatabase.productsDao().deleteproduct(productEntity);

                if (onDeleteListener != null){

                    onDeleteListener.OnDeletelistener(productEntity);
                }


               // productHistory.getSupportFragmentManager().beginTransaction().replace(R.id.,)











            }
        });

    }

    @Override
    public int getItemCount() {
        return  productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productNameHis, productDescriptionHis,detls, productPricehis , delete, cosDetailProducts;
        public Button accept;
        public ImageView  img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productDescriptionHis = itemView.findViewById(R.id.item_short_desc);
            productNameHis = itemView.findViewById(R.id.product_item_name);
            productPricehis = itemView.findViewById(R.id.product_item_price);
            delete = itemView.findViewById(R.id.tv_delete_product);
            detls = itemView.findViewById(R.id.tv_details_product);
            img = itemView.findViewById(R.id.product_thumb);






        }
    }
}
