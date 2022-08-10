package com.mzitow.foodsandcosmeticjungle.adabters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    List<CartEnity> cartEnities;

    public CartItemAdapter(List<CartEnity> cartEnities) {
        this.cartEnities = cartEnities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.cartName.setText(cartEnities.get(position).getCartName());
        holder.productCartDescription.setText(cartEnities.get(position).getCartDescription());
        holder.productCartPrice.setText(cartEnities.get(position).getCartPrice());



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cartName, productCartDescription,productCartPrice ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartName = itemView.findViewById(R.id.product_item_name);
            productCartDescription = itemView.findViewById(R.id.item_short_desc);
            productCartPrice = itemView.findViewById(R.id.product_item_price);
        }
    }
}
