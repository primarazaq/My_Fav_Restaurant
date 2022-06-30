package com.example.if3tugas2akb10119124;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import if3tugas2akb10119124.R;

//10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
public class SplashScreen extends AppCompatActivity {
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Slider.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}