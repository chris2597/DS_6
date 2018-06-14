package com.example.christopher.parcial1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView _lista;
    ListView _lstFile;
    File _f; // directorio principal
    String _raiz; // raiz
    private List<String> _fileList = new ArrayList<String>();
    private List<String> _pathList = new ArrayList<String>();
    final String tipoFichero = ".mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lista = (ListView) findViewById(R.id.lstvdir);

        _raiz = Environment.getExternalStorageDirectory().toString();

        Log.d("Directorio: ", _raiz.toString());

        _f = new File(_raiz);

        buscarArchivo(_f);

        ArrayAdapter<String> FileList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _fileList);
        _lista.setAdapter(FileList);

    }

     void buscarArchivo(File _path) {
      File[] newList = _path.listFiles();

        if (newList != null) {

            for (int i = 0; i < newList.length; i++) {
                File audioFiles = newList[i];
                if (newList[i].isDirectory()) {

                    buscarArchivo(newList[i]);
                } else {

                    if (newList[i].getName().endsWith(".mp3")) {
                        _fileList.add(audioFiles.getName());
                    }
                }
            }
        }
    }
}
