package com.example.christopher.lab9_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ejemplo.db";
    public static final String TABLA_NOMBRES = "info";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_NOMBRE = "nombre";

    public AppSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String _SQL = "CREATE TABLE " + TABLA_NOMBRES + "(_id integer primary key autoincrement, nombre text not null, apellido text not null)";
                db.execSQL(_SQL);
    }
                @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }
}