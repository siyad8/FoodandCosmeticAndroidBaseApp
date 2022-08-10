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

public class DeliverySignUp extends AppCompatActivity {

    EditText delName, delpass, delEmail, delPhone;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Delivery Sign Up");

        delName = findViewById(R.id.et_deliveryusername);
        delpass = findViewById(R.id.et_deliverypassword);
        delEmail = findViewById(R.id.et_deliveryemail);
        delPhone = findViewById(R.id.et_deliveryphone);

        signup = findViewById(R.id.button_deliverysignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =  new Intent(DeliverySignUp.this, DeliveryLogin.class);
//                startActivity(intent);
//
//                Toast toast = Toast.makeText(DeliverySignUp.this, "signup succces", Toast.LENGTH_SHORT);
//                toast.show();
                UserEntity userEntity = new UserEntity();
                userEntity.setName(delName.getText().toString());
                userEntity.setPassword(delpass.getText().toString());
                userEntity.setEmail(delEmail.getText().toString());
                userEntity.setPhone(delPhone.getText().toString());

                if (validateInput(userEntity)){

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao =userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            Toast toast = Toast.makeText(getApplicationContext(), "User Register successful", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(getApplicationContext(), ProducerLogin.class);
                            startActivity(intent);


                        }
                    }).start();;

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent =  new Intent(DeliverySignUp.this, DeliveryLogin.class);
                      startActivity(intent);


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