package com.mzitow.foodsandcosmeticjungle;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductDao;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ImageDao;
import com.mzitow.foodsandcosmeticjungle.database.ImageEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductsDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.database.UserEntity;
import com.mzitow.foodsandcosmeticjungle.model.LoadImage;
import com.mzitow.foodsandcosmeticjungle.model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddProduct extends AppCompatActivity {
    EditText productName, productPrice, productDiscription;
    Button submit, submitAsfood;
    SwitchCompat switchCompat;
    ImageView img, images;
    Bitmap bitmap ;




    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 1;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Products");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();




        productName = findViewById(R.id.et_productname);
        productPrice = findViewById(R.id.et_productprice);
        productDiscription = findViewById(R.id.et_productdescrition);
        submit = findViewById(R.id.button_addproduct);
        switchCompat = findViewById(R.id.switch_button_food);
        submitAsfood = findViewById(R.id.button_addfoodt);
        img = findViewById(R.id.addimg);
        images = findViewById(R.id.image);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();




            }
        });

        submit.setVisibility(View.INVISIBLE);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    submitAsfood.setVisibility(View.INVISIBLE);
                    submit.setVisibility(View.VISIBLE);
                }else {

                    submitAsfood.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);




                }
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setProductName(productName.getText().toString());
                productEntity.setProductDescription(productDiscription.getText().toString());
                productEntity.setProductPrice(productPrice.getText().toString());
                uploadImage();

                if (validateInput(productEntity)){

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    ProductsDao productsDao = userDatabase.productsDao();



                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            productsDao.addproduct(productEntity);
//                            Toast toast = Toast.makeText(getApplicationContext(), "submitted successful", Toast.LENGTH_SHORT);
//                            toast.show();
                            Intent intent = new Intent(getApplicationContext(), ProducerDashboard.class);
                            startActivity(intent);




                        }
                    }).start();;

                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
        });




        submitAsfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FoodProductEntity foodProductEntity = new FoodProductEntity();
                foodProductEntity.setProductName(productName.getText().toString());
                foodProductEntity.setProductDescription(productDiscription.getText().toString());
                foodProductEntity.setProductPrice(productPrice.getText().toString());
                uploadImage();

                if (validateFoodInput(foodProductEntity)){

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    FoodProductDao foodProductDao = userDatabase.foodProductDao();



                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            foodProductDao.addFoodProduct(foodProductEntity);
//                            Toast toast = Toast.makeText(getApplicationContext(), "submitted successful", Toast.LENGTH_SHORT);
//                            toast.show();
                            Intent intent = new Intent(getApplicationContext(), ProducerDashboard.class);
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
    private  boolean validateInput(ProductEntity productEntity){
        if ( productEntity.getProductName().isEmpty() || productEntity.getProductDescription().isEmpty()  || productEntity.getProductPrice().isEmpty()  ){
            return false;
        }

        return true;

    }
    private  boolean validateFoodInput(FoodProductEntity productEntity){
        if ( productEntity.getProductName().isEmpty() || productEntity.getProductDescription().isEmpty()  || productEntity.getProductPrice().isEmpty()  ){
            return false;
        }

        return true;

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

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    @Override
    public void onBackPressed() {
          finish();
        super.onBackPressed();
    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);



    }




    // Override onActivityResult method
    @SuppressLint("RestrictedApi")
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            Log.d(TAG, "onActivityResult:  looking for response");

            try {

                // Setting image on image view using Bitmap

                     bitmap = MediaStore.Images.Media.getBitmap(
                                getContentResolver(),
                                filePath);
               images.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    // UploadImage method
    private void uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AddProduct.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(AddProduct.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }

    }


}