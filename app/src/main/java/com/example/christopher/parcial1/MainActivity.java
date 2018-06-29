package com.example.christopher.parcial1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView _lista;
    String root;
    String[] items;
    final String audioMp3 = ".mp3";

    //Se inicializan todos los objetos de la lista donde se almacenarán los audios del dispositivo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lista = (ListView) findViewById(R.id.lstvdir);
        final ArrayList<File> listMusic = findMusicTracks(Environment.getExternalStorageDirectory());
        items = new String[listMusic.size()];

        for (int i=0; i<listMusic.size(); i++){

            items[i] = listMusic.get(i).getName().toString().replace(audioMp3, "").toLowerCase();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listmusic, R.id.textView, items);
        _lista.setAdapter(adapter);

        _lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), musicPlayer.class).putExtra("pos", position).putExtra("canciones", listMusic));
            }
        });

    }

    //Se buscan los audios en el dispositivo y se almacena en una lista de forma recursiva
    public ArrayList<File> findMusicTracks(File root) {

        ArrayList<File> listMusic = new ArrayList<>();
        File[] files = root.listFiles();

        for (File _lista: files) {

            if(_lista.isDirectory()  && !_lista.isHidden()) {

                listMusic.addAll(findMusicTracks(_lista));
            } else {

                if(_lista.getName().endsWith(audioMp3)){
                    listMusic.add(_lista);
                }
            }
            
        }
        return listMusic;
    }
}

