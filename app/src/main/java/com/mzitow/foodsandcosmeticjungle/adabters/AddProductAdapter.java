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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mzitow.foodsandcosmeticjungle.ProductCosmeticDiscription;
import com.mzitow.foodsandcosmeticjungle.ProductDiscription;
import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.Upload;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.ViewHolder> {
    List<ProductEntity> productsList;
    Context cont;

    private ArrayList<Model> mList;



  // private DatabaseReference root = FirebaseDatabase.getInstance().getReference("uploads");



    public AddProductAdapter(List<ProductEntity> productsList, Context cons , ArrayList<Model> mlist) {
        this.productsList = productsList;
        this.cont = cons;
        this.mList=mlist;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_product_card_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.productName.setText(productsList.get(position).getProductName());
        holder.productDescription.setText(productsList.get(position).getProductDescription());
        holder.productPrice.setText(productsList.get(position).getProductPrice());

        //Upload upload = mList.get(position);
//        Model model = mList.get(position);

      //  Glide.with(cont).load(mList.get(position).getImageUrl()).into(holder.imageview3);
       // holder.imageview3.setImageBitmap(mList.get(position));

        holder.detl.setOnClickListener(new View.OnClickListener() {
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





    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productDescription,productPrice , cosDetailProducts;
        public Button accept,decline;
        public ImageView detl, imageview3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txtProductName);
            productDescription = itemView.findViewById(R.id.txtProductDiscription);
            productPrice = itemView.findViewById(R.id.txtProductPrice);
            detl = itemView.findViewById(R.id.imgmore);
            imageview3 = itemView.findViewById(R.id.imgProductImage);


        }
    }
}
