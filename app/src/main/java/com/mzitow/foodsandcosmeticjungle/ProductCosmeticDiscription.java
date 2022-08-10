package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mzitow.foodsandcosmeticjungle.database.CartDao;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;

public class ProductCosmeticDiscription extends AppCompatActivity {
    TextView tiltecos ,  dicrpcos, pricecos, checkout, itemCountcos, checkoutAmount;
    Button addToCartcos;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cosmetic_discription);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product Description");

        tiltecos = findViewById(R.id.cosmeticItemTitle);
        dicrpcos = findViewById(R.id.tv_cosdescription);
        pricecos = findViewById(R.id.cosPrice);
        checkout = findViewById(R.id.checkout);
        addToCartcos= findViewById(R.id.button_first_cos);
        itemCountcos = findViewById(R.id.item_count);
        checkoutAmount = findViewById(R.id.checkout_amount);


        tiltecos.setText(getIntent().getStringExtra("name"));
        dicrpcos.setText(getIntent().getStringExtra("description"));
        pricecos.setText(getIntent().getStringExtra("price"));

        String name = String.valueOf(tiltecos);
        String decription = String.valueOf(dicrpcos);
        String prices = String.valueOf(pricecos);


        addToCartcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartEnity cartEnity = new CartEnity();
                cartEnity.setCartName(name);
                cartEnity.setCartDescription(decription);
                cartEnity.setCartPrice(prices);


                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                CartDao cartDao = userDatabase.cartDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        cartDao.addproduct(cartEnity);


                        count++;








                    }
                }).start();

                itemCountcos.setText(String.valueOf(count));



                checkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), PlaceYourOrder.class));
                    }
                });
            }
        });








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
                startActivity(new Intent(getApplicationContext(),ConsumerCategory.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}


