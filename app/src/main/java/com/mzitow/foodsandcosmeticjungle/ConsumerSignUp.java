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

public class ConsumerSignUp extends AppCompatActivity {
    EditText email, username,password,phone;
    Button signup;
    DBHelper DB;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_sign_up);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Consumer Sign up");



        email= findViewById(R.id.et_consumeremail);
        username= findViewById(R.id.et_consumerusername);
        password= findViewById(R.id.et_consumerpassword);
        phone= findViewById(R.id.et_consumerphone);
        signup= findViewById(R.id.button_consumer_signup);

//        String user = username.getText().toString();
//        String pass = password.getText().toString();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(username.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setEmail(email.getText().toString());
                userEntity.setPhone(phone.getText().toString());

                if (validateInput(userEntity)){

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao =userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                             userDao.registerUser(userEntity);
                          //  Toast toast = Toast.makeText(ConsumerSignUp.this, "User Register successful", Toast.LENGTH_SHORT);
                          //  toast.show();
                            Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                            startActivity(intent);



                        }
                    }).start();;

                }else{
                    Toast toast = Toast.makeText(ConsumerSignUp.this, "fill all the fields", Toast.LENGTH_SHORT);
                      toast.show();

                }

            }
        });

    }

    private  boolean validateInput(UserEntity userEntity){
        if ( userEntity.getName().isEmpty() || userEntity.getPassword().isEmpty() || userEntity.getEmail().isEmpty() ||userEntity.getPhone().isEmpty() ){
            return false;
        }

        return true;

    }

}