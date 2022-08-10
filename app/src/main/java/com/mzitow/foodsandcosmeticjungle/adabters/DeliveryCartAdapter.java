package com.mzitow.foodsandcosmeticjungle.adabters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.mzitow.foodsandcosmeticjungle.DeliveryProgress;
import com.mzitow.foodsandcosmeticjungle.ProductDiscription;
import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.DeliveryDataEntity;

import java.util.List;

public class DeliveryCartAdapter extends RecyclerView.Adapter<DeliveryCartAdapter.ViewHolder> {
    List<DeliveryDataEntity> deliveryDataEntities ;
    public Context dContext;


    public DeliveryCartAdapter(List<DeliveryDataEntity> deliveryDataEntities, Context D) {
        this.deliveryDataEntities = deliveryDataEntities;
        this.dContext = D;
    }

//    public DeliveryCartAdapter(Context Dc, List<DeliveryDataEntity> deliveryDataEntities) {
//        this.deliveryDataEntities = deliveryDataEntities;
//        this.dContext =Dc;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.delivery_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.productDeliveryName.setText(deliveryDataEntities.get(position).getName());
        holder.productDeliveryLocation.setText(deliveryDataEntities.get(position).getLocation());
        holder.productDeliveryContact.setText(deliveryDataEntities.get(position).getPhoneNo());
        holder.deliveryAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dContext, DeliveryProgress.class);
                intent.putExtra("Location", deliveryDataEntities.get(position).getLocation() );

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dContext.startActivity(new Intent(intent));
            }
        });
        holder.deliverDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dContext);
                builder.setMessage("would you wish to Decline the delivery");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(dContext, "the delivery would be passed to the next Delivery person ", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return deliveryDataEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productDeliveryName, productDeliveryLocation, productDeliveryContact;
        Button deliveryAcc , deliverDec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productDeliveryName = itemView.findViewById(R.id.delivery_item_name);
            productDeliveryLocation = itemView.findViewById(R.id.delivery_location);
            productDeliveryContact = itemView.findViewById(R.id.delivery_item_phone);
            deliveryAcc = itemView.findViewById(R.id.button_acceept);
            deliverDec = itemView.findViewById(R.id.button_delcine);





        }
    }
}
