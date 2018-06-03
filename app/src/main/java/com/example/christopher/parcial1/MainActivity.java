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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView _lista;
    ListView _lstFile;
    File _f;
    File[] _fs;
    String _raiz;
    private List<String> _fileList = new ArrayList<String>();
    private List<String> _pathList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _lista = (ListView) findViewById(R.id.lstvdir);
        _lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("New Path", _pathList.get(position).toString());
                File selected = new File(_pathList.get(position));
                _getFileList(_pathList.get(position));
            }
        });
        _raiz = Environment.getExternalStorageDirectory().toString();
        Log.d("Directorio: ", _raiz.toString());
        _getFileList(_raiz);
    }

    void _getFileList(String _path) {
        _fileList.clear();
        _pathList.clear();
        _f = new File(_path);
        _fs = _f.listFiles();
        for (int _i = 0; _i < _fs.length; _i++) {
            File _fl = _fs[_i];
            if (_fl.isDirectory()) {
                _pathList.add(_fl.getPath());
            } else if (_fl.isFile()) {
                _pathList.add("File " + _fl.getName());
                _fileList.add(_fl.getName());
            }
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, _pathList);
        _lista.setAdapter(directoryList);
    }
}