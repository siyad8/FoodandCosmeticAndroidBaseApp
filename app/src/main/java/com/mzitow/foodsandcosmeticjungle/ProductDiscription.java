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

public class ProductDiscription extends AppCompatActivity {

    TextView tilte ,  dicrp, price, checkout, itemCount, checkoutAmount;
    Button addToCart;


    public int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_discription);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product Description");

        tilte = findViewById(R.id.foodItemTitle);
        dicrp = findViewById(R.id.tv_description);
        price = findViewById(R.id.foodPrice);
        checkout = findViewById(R.id.checkout);
        addToCart= findViewById(R.id.button_first);
        itemCount = findViewById(R.id.item_count);
        checkoutAmount = findViewById(R.id.checkout_amount);


        tilte.setText(getIntent().getStringExtra("name"));
        dicrp.setText(getIntent().getStringExtra("description"));
        price.setText(getIntent().getStringExtra("price"));

        String name = String.valueOf(tilte);
        String decription = String.valueOf(dicrp);
        String prices = String.valueOf(price);


        addToCart.setOnClickListener(new View.OnClickListener() {
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

                itemCount.setText(String.valueOf(count));



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
                startActivity(new Intent(getApplicationContext(),CustomerFoodCatergory.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}