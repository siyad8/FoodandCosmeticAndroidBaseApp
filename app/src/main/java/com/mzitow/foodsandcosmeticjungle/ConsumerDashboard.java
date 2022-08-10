package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.VideoView;

public class ConsumerDashboard extends AppCompatActivity {
     Button food, cosmetics;
    TextView title;
    VideoView videoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_dashboard);

        videoView2=findViewById(R.id.videoview2);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_video);
        videoView2.setVideoURI(uri);
        videoView2.start();

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });




//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Consumer Dashboard");

        food = findViewById(R.id.foodcard);
        cosmetics = findViewById(R.id.cosmeticcard);


        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsumerDashboard.this, FoodTabLayOut.class);
                startActivity(intent);
            }
        });
        cosmetics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsumerDashboard.this, CosmeticsTabLayOut.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onResume() {
        videoView2.resume();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        videoView2.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        videoView2.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        videoView2.stopPlayback();
        super.onDestroy();
    }


}