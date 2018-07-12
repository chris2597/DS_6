package com.example.christopher.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button _btnmessagebox;
    AlertDialog.Builder _alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnmessagebox = (Button) findViewById(R.id.btnmessage);
        _alert = new AlertDialog.Builder(this);
        _alert.setTitle("Mensaje del Sistema");
        _alert.setMessage("Bienvenidos al Mundo Android");
        _alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Confirmación:", "Positiva");
            }
        });
        _alert.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        _btnmessagebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _alert.show();
            }
        });
    }
}