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

public class CustomerLogin extends AppCompatActivity {
    EditText username, password;
    TextView register;
    Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consumer Login");

        username = findViewById(R.id.et_consumerloginusername);
        password = findViewById(R.id.et_consumerloginpassword);
        signIn = findViewById(R.id.button_consumersignin);
        register = findViewById(R.id.regeistertxt);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLogin.this, ConsumerSignUp.class);
                startActivity(intent);

            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

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
//                                String titleName = userEntity.n;
                                Intent intent = new Intent(getApplicationContext(), ConsumerDashboard.class);
                                startActivity(intent);


                            }


                        }
                    }).start();;
                }


//






//

            }
        });

    }
}