package com.mzitow.foodsandcosmeticjungle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ProducerLoginNew extends AppCompatActivity {


    FirebaseAuth auth;
    EditText email, password;
    TextView forget;
    TextView register;
    Button login;
    String email1,password1;
    TextView otp;
    CheckBox checkBox;
    SharedPreferences ref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        auth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_login_new);
        email =(EditText)findViewById(R.id.email);
        password =(EditText)findViewById(R.id.pass);
        login =(Button)findViewById(R.id.btn1);
        forget =(TextView) findViewById(R.id.forget) ;
        register =(TextView) findViewById(R.id.dont);
        otp =(TextView) findViewById(R.id.otp) ;
//        ref =  getSharedPreferences("MyAPP",MODE_PRIVATE);
//        editor =ref.edit();
//        checkBox=(CheckBox)findViewById(R.id.check);
//        SharedPreferences ref1 = getSharedPreferences("MyAPP",MODE_PRIVATE);
//        String check1=ref1.getString("remember","");
//        if(check1.equals("true"))
//        {
//            startActivity(new Intent(ProducerLoginNew.this,ProducerDashboard.class));
//
//        }
//        else{
//            //Toast.makeText(Login.this,"Login Please",Toast.LENGTH_LONG).show();
//        }
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent o = new Intent(ProducerLoginNew.this,OneTimePassword.class);
                startActivity(o);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProducerLoginNew.this,ProducerSignUpNew.class);
                startActivity(i);
            }
        });

//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(buttonView.isChecked())
//                {
//                    editor.putString("remember","true");
//                    editor.apply();
//                }
//                else if(!buttonView.isChecked())
//                {
//                    editor.putString("remember","false");
//                    editor.apply();
//                }
//            }
//        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email1=email.getText().toString();
                password1=password.getText().toString();
                if(!email1.contains("@"))
                {
                    Toast.makeText(ProducerLoginNew.this, "Please Enter Valid Email", Toast.LENGTH_LONG).show();
                }
                else if(password1.length()<6)
                {
                    Toast.makeText(ProducerLoginNew.this,"Incorrect Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    CheckUser(email1,password1);
                }


            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProducerLoginNew.this,Reset_password.class));

            }
        });

    }

    private void CheckUser(String email1, String password1) {
        auth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Intent i = new Intent(ProducerLoginNew.this,ProducerDashboard.class);
                    startActivity(i);
                    Toast.makeText(ProducerLoginNew.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                    email.setText("");
                    password.setText("");
                }
                else{
                    Toast.makeText(ProducerLoginNew.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProducerLoginNew.this,"Error"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}