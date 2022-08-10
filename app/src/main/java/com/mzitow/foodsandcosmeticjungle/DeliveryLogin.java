package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mzitow.foodsandcosmeticjungle.database.UserDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.database.UserEntity;

public class DeliveryLogin extends AppCompatActivity {

    EditText deliusername, delipassweord;
    TextView tittle , deliverysignupPage;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delivery Login");

        login =  findViewById(R.id.button_deliverysignin);

        deliusername = findViewById(R.id.et_delilogusername);
        delipassweord = findViewById(R.id.et_delilogpassword);
       // tittle = findViewById(R.id.delivery_title);
        deliverysignupPage = findViewById(R.id.delivery_sign_up_page);

        deliverysignupPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsumerSignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DeliveryLogin.this, DeliveryDashboard.class);
//                startActivity(intent);
//
//                Toast toast = Toast.makeText(DeliveryLogin.this, "signup succces", Toast.LENGTH_SHORT);
//                toast.show();
                String user = deliusername.getText().toString();
                String pass = delipassweord.getText().toString();



                if(user.isEmpty() || pass.isEmpty() ){

                    Toast toast = Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(user,pass);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(getApplicationContext(), "invalid Credentials ", Toast.LENGTH_SHORT);
                                        toast.show();

                                    }
                                });

                            } else {
                               // String titleName = userEntity.name;
                                Intent intent = new Intent(getApplicationContext(), DeliveryDashboard.class);
                                startActivity(intent);


                            }


                        }
                    }).start();;
                }



            }
        });




    }
}