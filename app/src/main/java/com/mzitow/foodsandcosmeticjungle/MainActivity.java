package com.mzitow.foodsandcosmeticjungle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    Button consumer, producer, delivery;
    VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consumer = findViewById(R.id.button_consumer);
        producer = findViewById(R.id.button_producer);
        delivery = findViewById(R.id.button_delivery);

      //  getSupportActionBar().hide();

        videoView=findViewById(R.id.videoview);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg_video);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });




        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consume = new Intent(MainActivity.this, CustomerLoginNew.class);
                startActivity(consume);
            }
        });
        producer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pooducer = new Intent(MainActivity.this, ProducerLoginNew.class);
                startActivity(pooducer);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliver = new Intent(MainActivity.this, DeliveryLoginNew.class);
                startActivity(deliver);
            }
        });


    }

    @Override
    protected void onResume() {
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        videoView.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        videoView.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }
}