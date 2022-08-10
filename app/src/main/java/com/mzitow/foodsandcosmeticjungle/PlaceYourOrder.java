package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mzitow.foodsandcosmeticjungle.adabters.CartItemAdapter;
import com.mzitow.foodsandcosmeticjungle.database.CartDao;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.DeliveryDao;
import com.mzitow.foodsandcosmeticjungle.database.DeliveryDataEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;

import java.util.List;

public class PlaceYourOrder extends AppCompatActivity {
    SwitchCompat switchCompat;
    EditText name, address, phone;
    RecyclerView recyclerView;
    AlertDialog.Builder builder;
    Button placeButton, pickupButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Place your Order");
        builder = new AlertDialog.Builder(this);

        name = findViewById(R.id.inputName);
        address = findViewById(R.id.inputAddress);
        phone = findViewById(R.id.inputPhone);
        recyclerView =  findViewById(R.id.cartItemsRecyclerView);
        switchCompat = findViewById(R.id.switchDelivery);
        placeButton = findViewById(R.id.buttonPlaceYourOrder);
        pickupButton = findViewById(R.id.buttonDelivery);

        placeButton.setVisibility(View.INVISIBLE);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    placeButton.setVisibility(View.VISIBLE);
                    pickupButton.setVisibility(View.INVISIBLE);
                }else{
                    placeButton.setVisibility(View.INVISIBLE);
                    pickupButton.setVisibility(View.VISIBLE);

                }

            }
        });

        placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                DeliveryDao deliveryDao = userDatabase.deliveryDao();
                DeliveryDataEntity deliveryDataEntity = new DeliveryDataEntity();
                deliveryDataEntity.setName(name.getText().toString());
                deliveryDataEntity.setLocation(address.getText().toString());
                deliveryDataEntity.setPhoneNo(phone.getText().toString());
                deliveryDao.addDeliveryInfo(deliveryDataEntity);
                startActivity( new Intent(getApplicationContext(),ConsumerDashboard.class));
            }


        });

        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("will you wish to pick up your product")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),"please pick your product from consumer",Toast.LENGTH_SHORT)
                                        .show();
                                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                                CartDao cartDao = userDatabase.cartDao();
                                cartDao.deleteAll();
                                startActivity(new Intent(getApplicationContext(), ConsumerDashboard.class));

                                //startActivities(new Intent[PlaceYourOrder.this,ConsumerDashboard.class]);



                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Product Delivery");
                alertDialog.show();


            }
        });
        initRecycler();











    }
    public void placeorder(){

       // if (  !switchCompat.isChecked()){
        //if it checked pick up button funtion else add to database




//        }else {
//            UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
//            DeliveryDao deliveryDao = userDatabase.deliveryDao();
//            DeliveryDataEntity deliveryDataEntity = new DeliveryDataEntity();
//            deliveryDataEntity.setName(name.getText().toString());
//            deliveryDataEntity.setLocation(address.getText().toString());
//            deliveryDataEntity.setPhoneNo(phone.getText().toString());
//            deliveryDao.addDeliveryInfo(deliveryDataEntity);
//            startActivity( new Intent(getApplicationContext(),ConsumerDashboard.class));
//
//        }






    }
    public void initRecycler(){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        CartDao cartDao = userDatabase.cartDao();
        List<CartEnity> cartEnities = cartDao.getAllProductsList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartItemAdapter cartItemAdapter = new CartItemAdapter(cartEnities);
        recyclerView.setAdapter(cartItemAdapter);

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
                startActivity(new Intent(getApplicationContext(),ConsumerDashboard.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}