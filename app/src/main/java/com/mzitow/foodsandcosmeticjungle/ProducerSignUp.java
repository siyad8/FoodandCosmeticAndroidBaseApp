package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mzitow.foodsandcosmeticjungle.database.UserDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.database.UserEntity;

public class ProducerSignUp extends AppCompatActivity {
    EditText producerEmail, producerName, producerPass,producerPhone;

    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Producer Sign Up");

        producerEmail = findViewById(R.id.et_produceremail);
        producerName = findViewById(R.id.et_producerusername);
        producerPass = findViewById(R.id.et_producerpassword);
        producerPhone = findViewById(R.id.et_producerphone);


        signup = findViewById(R.id.button_producersignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ProducerSignUp.this, ProducerLogin.class);
//                startActivity(intent);
//
//                Toast toast = Toast.makeText(ProducerSignUp.this, "signup succces", Toast.LENGTH_SHORT);
//                toast.show();

                UserEntity producerEntity = new UserEntity();
                producerEntity.setName(producerName.getText().toString());
                producerEntity.setPassword(producerPass.getText().toString());
                producerEntity.setEmail(producerEmail.getText().toString());
                producerEntity.setPhone(producerPhone.getText().toString());

                if (validateInput(producerEntity)){

                  UserDatabase producerDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                   UserDao producerDao = producerDatabase.userDao();
                            //userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            ProducerDao.r(producerEntity);
                            Toast toast = Toast.makeText(getApplicationContext(), "User Register successful", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(getApplicationContext(), ProducerLogin.class);
                            startActivity(intent);


                        }
                    }).start();;

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        });
    }
    private  boolean validateInput(UserEntity producerEntity){
        if ( producerEntity.getName().isEmpty() || producerEntity.getPassword().isEmpty() || producerEntity.getEmail().isEmpty() ||producerEntity.getPhone().isEmpty() ){
            return false;
        }

        return true;

    }
}