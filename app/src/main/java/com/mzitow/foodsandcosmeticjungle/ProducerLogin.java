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

public class ProducerLogin extends AppCompatActivity {

    Button login;
    EditText producerlogName, producerlogpass;
    TextView producerSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Producer Login");

        login = findViewById(R.id.button_producersignin);
        producerlogName = findViewById(R.id.et_producerlogusername);
        producerlogpass = findViewById(R.id.et_producerlogpassword);
        producerSignUp = findViewById(R.id.producer_sign_up_page);

        producerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ProducerSignUp.class);
                startActivity(intent);

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ProducerLogin.this, ProducerDashboard.class);
//                startActivity(intent);
//
//                Toast toast = Toast.makeText(ProducerLogin.this, "signup succces", Toast.LENGTH_SHORT);
//                toast.show();

                String user = producerlogName.getText().toString();
                String pass =  producerlogpass.getText().toString();

                if(user.isEmpty() || pass.isEmpty() ){

                    Toast toast = Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                   UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                 UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            UserEntity userEntity = userDao.login(user,pass);
                           UserEntity producerEntity = userDao.login(user, pass);
                            if (producerEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(getApplicationContext(), "invalid Credentials ", Toast.LENGTH_SHORT);
                                        toast.show();

                                    }
                                });

                            } else {
                             //   String titleName = producerEntity.name;
                                Intent intent = new Intent(getApplicationContext(), ProducerDashboard.class);
                                startActivity(intent);


                            }


                        }
                    }).start();;
                }



            }
        });

    }
}
