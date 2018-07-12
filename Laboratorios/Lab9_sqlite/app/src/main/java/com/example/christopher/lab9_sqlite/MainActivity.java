package com.example.christopher.lab9_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button _btnNuevo, _btnGuardar, _btnSiguiente, _btnAnterior, _btnEliminar;
    EditText _txtNombre, _txtApellido;
    Cursor _dbCursor = null;
    int _id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _btnNuevo = (Button) findViewById(R.id.btnNuevo);
        _btnGuardar = (Button) findViewById(R.id.btnGuardar);
        _btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        _btnAnterior = (Button) findViewById(R.id.btnAnterior);
        _btnEliminar = (Button) findViewById(R.id.btnEliminar);
        _txtNombre = (EditText) findViewById(R.id.txtNombre);
        _txtApellido = (EditText) findViewById(R.id.txtApellido);
        _GetAllData();
        _btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _txtApellido.setText("");
                _txtNombre.setText("");
            }
        });
        _btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean _sw = false;
                _sw = _SetData();
            }
        });
        _btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_dbCursor.moveToNext()) {
                    _txtApellido.setText(_dbCursor.getString(2));
                    _txtNombre.setText(_dbCursor.getString(1));
                    _id = _dbCursor.getInt(0);
                }
            }
        });
        _btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_dbCursor.moveToPrevious()) {
                    _txtApellido.setText(_dbCursor.getString(2));
                    _txtNombre.setText(_dbCursor.getString(1));
                    _id = _dbCursor.getInt(0);
                }
            }
        });
        _btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_id > 0) {
                    _DeleteRecord();
                    _GetAllData();
                }
            }
        });
    }

    public Boolean _DeleteRecord() {
        Boolean _sw = false;
        AppSQLiteOpenHelper _SQLite = new AppSQLiteOpenHelper(this);
        if (_id != 0) {
            try {
                SQLiteDatabase _db = _SQLite.getWritableDatabase();
                int _result = _db.delete("info", "_id = " + String.valueOf(_id), null);
                _db.close();
                if (_result > 0) {
                    _sw = true;
                    this._txtApellido.setText("");
                    this._txtNombre.setText("");
                }
            } catch (Exception _ex) {
            }
        }
        return _sw;
    }

    public void _GetAllData() {
        AppSQLiteOpenHelper _SQLite = new AppSQLiteOpenHelper(this);
        SQLiteDatabase _db = _SQLite.getWritableDatabase();
        _dbCursor = null;
        try {
            _dbCursor = _db.rawQuery("SELECT * FROM info ORDER BY nombre", null);
            if (_dbCursor != null) {
                _dbCursor.moveToFirst();
            }
        } catch (Exception _ex) {
        }
    }

    public Boolean _SetData() {
        Boolean _sw = false;
        AppSQLiteOpenHelper _SQLite = new AppSQLiteOpenHelper(this);
        if (this._txtNombre.getText().toString() != "" && this._txtApellido.getText().toString() != "") {
            try {
                SQLiteDatabase _db = _SQLite.getWritableDatabase();
                ContentValues _rows = new ContentValues();
                _rows.put("nombre", this._txtNombre.getText().toString());
                _rows.put("apellido", this._txtApellido.getText().toString());
                _db.insert("info", null, _rows);
                _db.close();
                _sw = true;
                this._txtApellido.setText("");
                this._txtNombre.setText("");
                this._GetAllData();
            } catch (Exception _ex) {
            }
        } else {
        }
        return _sw;
    }
}
