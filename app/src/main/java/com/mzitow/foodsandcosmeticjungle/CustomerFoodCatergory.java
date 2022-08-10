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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mzitow.foodsandcosmeticjungle.adabters.AddProductAdapter;
import com.mzitow.foodsandcosmeticjungle.adabters.CustomerFoodAdapter;
import com.mzitow.foodsandcosmeticjungle.adabters.WhatsNewAdapter;
import com.mzitow.foodsandcosmeticjungle.database.CartDao;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.database.WhatsNewDao;
import com.mzitow.foodsandcosmeticjungle.database.WhatsNewEntity;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class CustomerFoodCatergory extends AppCompatActivity {
    RecyclerView customerfoodView, whatsNew;
    TextView tv;
    Button button;
   WhatsNewEntity whatsNewEntity = new WhatsNewEntity();
    ArrayList<Model> list;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_food_catergory);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consumer Foods");

         customerfoodView = findViewById(R.id.my_foodProductrecycler);
         whatsNew = findViewById(R.id.my_WhatsNewRecycler);

         new Thread(new Runnable() {
             @Override
             public void run() {
                 for (int i =0; i<10; i++){

                     // whatsNewEntities.add(i,  new WhatsNewEntity("name", "221","short Description")
                     //);



                     UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                     WhatsNewDao whatsNewDao = userDatabase.whatsNewDao();

                     whatsNewEntity.setProductName("name");
                     whatsNewEntity.setProductDescription("short Description");
                     whatsNewEntity.setProductPrice("123");

                     whatsNewDao.addproduct(whatsNewEntity);
                 }

             }
         }).start();

        button = findViewById(R.id.checkout);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //placeYourOder Activity goes here
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                CartDao cartDao = userDatabase.cartDao();

                List<CartEnity> cartEnities=  cartDao.getAllProductsList();

                //placeYourOder Activity goes here
                //
                if (cartEnities.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please add to cart",Toast.LENGTH_SHORT).show();

                }else{
                    startActivity(new Intent(getApplicationContext(), PlaceYourOrder.class));

                }


            }
        });



        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
          List<FoodProductEntity> foodProductEntities = userDatabase.foodProductDao().getAllProductsList();
          customerfoodView.setLayoutManager(new LinearLayoutManager(this));
          CustomerFoodAdapter customerFoodAdapter = new CustomerFoodAdapter(foodProductEntities,getApplicationContext(),list);
          customerfoodView.setAdapter(customerFoodAdapter);
//
//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Model model = dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }
//                customerFoodAdapter.notifyDataSetChanged();
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//
//        });


       // UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        List<WhatsNewEntity> whatsNewEntities = userDatabase.whatsNewDao().getAllProductsList();
        whatsNew = findViewById(R.id.my_WhatsNewRecycler);
        whatsNew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        WhatsNewAdapter whatsNewAdapter = new WhatsNewAdapter(whatsNewEntities);
        whatsNew.setAdapter(whatsNewAdapter);









    }
    public void intiRecycler(){




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