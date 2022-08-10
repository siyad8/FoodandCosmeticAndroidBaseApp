package com.mzitow.foodsandcosmeticjungle;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.room.TypeConverter;

import com.mzitow.foodsandcosmeticjungle.database.ImageDao;
import com.mzitow.foodsandcosmeticjungle.database.ImageEntity;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;
import com.mzitow.foodsandcosmeticjungle.model.LoadImage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImageBitmapString extends Activity {
//    ArrayList  bitmaps;
//    ArrayList imageSources;
//
//    @TypeConverter
//    public static String BitMapToString(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String temp = Base64.encodeToString(b, Base64.DEFAULT);
//        if (temp == null) {
//            return null;
//        } else
//            return temp;
//    }
//
//    public void loadImagesFromGallery() {
//
//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE) !=              PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(ImageBitmapString.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                    100);
//            return;
//        }
//
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setType("image/*");
//        startActivityForResult(intent, 1);
//
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            //   imageFragmentContainer.setVisibility(View.VISIBLE);
//            bitmaps = new ArrayList<>();
//            imageSources = new ArrayList<>();
//            ClipData clipData = data.getClipData();
//            //clip data will be null if user select one item from gallery
//
//            if (clipData != null) {
//                for (int i = 0; i < clipData.getItemCount(); i++) {
//                    Uri imageUri = clipData.getItemAt(i).getUri();
//                    InputStream is = null;
//                    try {
//                        is = getContentResolver().openInputStream(imageUri);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    bitmaps.add(bitmap);
//                    String imageSource = ImageBitmapString.BitMapToString(bitmap);
//                    imageSources.add(imageSource);
//
//                }
//            } else {
//                Uri imageUri = data.getData();
//                try {
//                    InputStream is = getContentResolver().openInputStream(imageUri);
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    bitmaps.add(bitmap);
//                    String imageSource = ImageBitmapString.BitMapToString(bitmap);
//                    imageSources.add(imageSource);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                ImageDao image_dao = UserDatabase.getUserDatabase(this).imageDao();
//                ImageEntity image = new ImageEntity(imageSources);
//                image_dao.insert(image);
//                finish();
//            }
//        }
//    }
//
//
//
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}

