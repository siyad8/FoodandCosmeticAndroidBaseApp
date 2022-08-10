package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mzitow.foodsandcosmeticjungle.adabters.AddProductAdapter;
import com.mzitow.foodsandcosmeticjungle.adabters.DeliveryCartAdapter;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.DeliveryDataEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductsDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;

import java.util.List;

public class DeliveryDashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView txInfo;
    //List<ProductEntity> productsList;
   // ProductsDao productsDao;
   // UserDatabase userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_dashboard);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delivery Dashboard");

        txInfo = findViewById(R.id.tv_info);

        // Recyclerview for test goes here

      //  productsList= (List<ProductEntity>) userDatabase.productsDao().getAllProductsList();
             UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
             List<DeliveryDataEntity> deliveryDataEntities = userDatabase.deliveryDao().getAllDeliveryList();
            // List<CartEnity> cartEnities = userDatabase.cartDao().getAllProductsList();
             if(deliveryDataEntities.isEmpty()){

                 txInfo.setVisibility(View.VISIBLE);
             }
             else {
                 txInfo.setVisibility(View.INVISIBLE);
                 // note for remember delivery's list does not come from cart list
                 recyclerView = findViewById(R.id.delivery_recycler_view);
                 recyclerView.setLayoutManager(new LinearLayoutManager(this));
                 DeliveryCartAdapter deliveryCartAdapter = new DeliveryCartAdapter(deliveryDataEntities,this);
                 recyclerView.setAdapter(deliveryCartAdapter);



             }

//        ProductsDao productsDao = userDatabase.productsDao();
//        List<ProductEntity> productEntities =   userDatabase.productsDao().getAllProductsList();




//        recyclerView = findViewById(R.id.delivery_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        AddProductAdapter addProductAdapter = new AddProductAdapter(productEntities);
//        recyclerView.setAdapter(addProductAdapter);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
            case R.id.nav_products:
                startActivity(new Intent(getApplicationContext(),DeliveryLogin.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}