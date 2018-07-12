package com.example.christopher.lab8;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {
    Button _btn1;
    Button _btn2;
    MediaPlayer mp;
    MediaPlayer mp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btn1 = (Button) findViewById(R.id.btnPlay1);
        _btn2 = (Button) findViewById(R.id.btnPlay2);
        mp = MediaPlayer.create(this, R.raw.inoj);
        mp1 = MediaPlayer.create(this, R.raw.jarule);
        _btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
        _btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp1.start();
            }
        });
    }
}