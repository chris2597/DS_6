package com.example.christopher.lab4;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActiviy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler _handler = new Handler();
        _handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent _intent = new Intent(SplashActiviy.this, MainActivity.class);
                startActivity(_intent);
                finish();
            }
        }, 3000);
    }
}