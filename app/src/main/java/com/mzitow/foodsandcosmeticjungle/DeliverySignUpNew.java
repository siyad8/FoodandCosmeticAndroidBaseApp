package com.mzitow.foodsandcosmeticjungle;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class DeliverySignUpNew extends AppCompatActivity {

    EditText name ,email,password;
    Button register, cancel;
    FirebaseAuth auth;
    DatabaseReference dbref;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_sign_up_new);
        FirebaseApp.initializeApp(getApplicationContext());
        name = (EditText)findViewById(R.id.name);
        email =(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        cancel =(Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeliverySignUpNew.super.onBackPressed();
            }
        });
        dbref = FirebaseDatabase.getInstance().getReference().child("User");
        auth =FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                if(name1.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Name ",Toast.LENGTH_LONG).show();
                }
                else if(!email1.contains("@"))
                {
                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_LONG).show();

                }
                else if(password1.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Password must be greater than 6 char",Toast.LENGTH_LONG).show();
                }
                else{
                    AddUser(email1,password1,name1);
                }
            }
        });

    }

    private void AddUser(String email,String password,String Name) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            HashMap<String,String> user =new HashMap<>();
                            user.put("Name",Name);
                            user.put("email",email);
                            user.put("password",password);
                            dbref.push().setValue(user);
                            checkSum();

                        }
                        else{
                            Toast.makeText( DeliverySignUpNew.this,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText( DeliverySignUpNew.this,"Error"+e.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void checkSum() {
        Toast.makeText( DeliverySignUpNew.this,"Data Entered ",Toast.LENGTH_LONG).show();
        DeliverySignUpNew.super.onBackPressed();
    }
}