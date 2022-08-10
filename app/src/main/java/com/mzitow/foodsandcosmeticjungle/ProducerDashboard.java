package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ProducerDashboard extends AppCompatActivity {

    CardView viewmyproducts, addprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_dashboard);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Producer Dashboard");

        addprod = findViewById(R.id.addnewproducts);
        viewmyproducts = findViewById(R.id.myproducts);

        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);

            }
        });
        viewmyproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ProductHistory.class);
                startActivity(intent);


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
                startActivity(new Intent(getApplicationContext(),ProducerDashboard.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}