package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mzitow.foodsandcosmeticjungle.adabters.AddProductAdapter;
import com.mzitow.foodsandcosmeticjungle.adabters.DeliveryInfoAdapter;
import com.mzitow.foodsandcosmeticjungle.adabters.FoodHistoryAdapter;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.model.CustomModel;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ProductHistory extends AppCompatActivity implements DeliveryInfoAdapter.OnDeleteListener, FoodHistoryAdapter.OnDeletedListener {
    TextView tvInfo;
    private RecyclerView producerHistoryR;
    private RecyclerView foodHistory;
    CustomModel customModel = new CustomModel(this);
    ArrayList<Model> list;
  //  private DatabaseReference root = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_history);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("producer Products");

        tvInfo = findViewById(R.id.producer_producer_info_tv);
        producerHistoryR = (RecyclerView) findViewById(R.id.producer_order_history_recycler);
        foodHistory = (RecyclerView) findViewById(R.id.producer_history_recycler);


        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        List<ProductEntity> productEntityList = userDatabase.productsDao().getAllProductsList();
        List<FoodProductEntity> foodProductEntityList = userDatabase.foodProductDao().getAllProductsList();


        producerHistoryR.setLayoutManager(new LinearLayoutManager(this));
        DeliveryInfoAdapter deliveryInfoAdapter = new DeliveryInfoAdapter(productEntityList, this, getApplicationContext(), list);
        producerHistoryR.setAdapter(deliveryInfoAdapter);

        if (productEntityList.isEmpty()  ) {
            tvInfo.setVisibility(View.VISIBLE);

        } else if(foodProductEntityList.isEmpty()){

            tvInfo.setVisibility(View.VISIBLE);



//            root.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Model model = dataSnapshot.getValue(Model.class);
//                        list.add(model);
//                    }
//                    addProductAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//
//
//            });


        }
        else {
            tvInfo.setVisibility(View.INVISIBLE);


        }



        foodHistory.setLayoutManager(new LinearLayoutManager(this));
        FoodHistoryAdapter foodHistoryAdapter = new FoodHistoryAdapter( getApplicationContext(),foodProductEntityList,this, list);
        producerHistoryR.setAdapter(foodHistoryAdapter);


    }

    @Override
    public void OnDeletelistener (ProductEntity productEntity ){

        customModel.delete(productEntity);



    }



    @Override
    public void OnDeletedlistener(FoodProductEntity foodProductEntity) {

        customModel.deletefood(foodProductEntity);

    }
}