package com.example.christopher.parcial1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class musicPlayer extends AppCompatActivity implements View.OnClickListener {

    static MediaPlayer mp;
    ArrayList<File> listMusic;
    int position;
    Uri u;
    String aux = "";
    Thread updateSeekBar;

    ImageButton _btnPlay, _btnPause, _btnStop, _btnPlayList;
    TextView _name, _durationMusic, _continua;
    SeekBar _sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        _btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        _btnPause = (ImageButton) findViewById(R.id.btnPause);
        _btnStop = (ImageButton) findViewById(R.id.btnStop);
        _btnPlayList = (ImageButton) findViewById(R.id.btnPlayList);

        _name = (TextView) findViewById(R.id.name);
        _durationMusic = (TextView) findViewById(R.id.time);
        _continua = (TextView) findViewById(R.id.time2);

        _btnPlay.setOnClickListener(this);
        _btnPause.setOnClickListener(this);
        _btnStop.setOnClickListener(this);
        _btnPlayList.setOnClickListener(this);

        _sb = (SeekBar) findViewById(R.id.sb);

        updateSeekBar = new Thread(){

            @Override
            public void run() {
                super.run();
                int duration = mp.getDuration();
                _sb.setMax(duration);

                int actualPosition = 0;
                int ejecution = 0;
                boolean ban = false;

                while (actualPosition < duration){
                    try {
                        sleep(500);
                        actualPosition = mp.getCurrentPosition();
                        _sb.setProgress(actualPosition);

                        ejecution = _sb.getProgress();
                        aux = getHRM(ejecution);
                        _continua.setText(aux.toString().trim());
                    } catch (Exception e){

                        _continua.setText(aux);
                    }
                    _sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            mp.seekTo(seekBar.getProgress());
                        }
                    });
                }
            }
        };

        if (mp != null){

            mp.stop();
        }
        try{
            Intent i= getIntent();
            Bundle b = i.getExtras();
            listMusic = (ArrayList)b.getParcelableArrayList("canciones");
            position = (int) b.getInt("pos", 0);
            u = Uri.parse(listMusic.get(position).toString());
            _name.setText(listMusic.get(position).getName().toString());
            mp = MediaPlayer.create(getApplication(), u);
            updateSeekBar.start();
            mp.start();
            _durationMusic.setText(getHRM(mp.getDuration()));
        } catch (Exception e){

        }
    }

    private String getHRM(int miliseconds){

        int seconds = (int) (miliseconds / 1000 ) % 60;
        int minutes = (int) ((miliseconds / (1000*60 )) % 60);
        int hours = (int) ((miliseconds / (1000*60*60)) % 24);
        String aux="";
        aux = ((hours<10)?"0"+hours:hours) + ":" + ((minutes<10)?"0"+minutes:minutes) + ":"+ ((seconds<10)?"0"+seconds:seconds);
        return aux;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){

            case R.id.btnPlay:{
                if(!mp.isPlaying()) {
                    mp.start();
                }
                break;
            }

            case R.id.btnPause:{
                if(mp.isPlaying()){
                    mp.pause();
                }
                break;
            }

            case R.id.btnStop:{
                if(mp.isPlaying()){
                    mp.stop();
                    updateSeekBar.start();
                }
                break;
            }

        }

    }
}
