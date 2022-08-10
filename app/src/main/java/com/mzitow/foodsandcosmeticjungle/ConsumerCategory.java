package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
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
import com.mzitow.foodsandcosmeticjungle.adabters.WhatsNewAdapter;
import com.mzitow.foodsandcosmeticjungle.database.CartDao;
import com.mzitow.foodsandcosmeticjungle.database.CartEnity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.database.WhatsNewEntity;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ConsumerCategory extends AppCompatActivity {
    RecyclerView customerrecyclerView, whatsNewCategory;
    TextView tv;
    Button button;

    WhatsNewEntity whatsNewEntity = new WhatsNewEntity();

    ArrayList<Model> list;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_category);
       // layout =  findViewById(R.layout.view_bottom_anchor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consumer Cosmetics");
      //  actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.checkout);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i<10; i++){

                    // whatsNewEntities.add(i,  new WhatsNewEntity("name", "221","short Description")
                    //);

                    whatsNewEntity.setProductName("name");
                    whatsNewEntity.setProductDescription("short Description");
                    whatsNewEntity.setProductPrice("123");
                }

            }
        }).start();







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        List<ProductEntity> productEntities =   userDatabase.productsDao().getAllProductsList();
        customerrecyclerView = findViewById(R.id.my_foodrecycler);
        customerrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddProductAdapter addProductAdapter = new AddProductAdapter(productEntities,getApplicationContext(),list);
        customerrecyclerView.setAdapter(addProductAdapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                addProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        initRe();





    }
    public void initRe(){
        UserDatabase userDatabase1 = UserDatabase.getUserDatabase(getApplicationContext());
        List<WhatsNewEntity> whats = userDatabase1.whatsNewDao().getAllProductsList();
        whatsNewCategory = findViewById(R.id.my_newrecycler);
        whatsNewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        WhatsNewAdapter whatsNewAdapter = new WhatsNewAdapter(whats);
        whatsNewCategory.setAdapter(whatsNewAdapter);

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