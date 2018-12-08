package com.example.tecsup.glendajuliapractica03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "telefonos.db";
    private static final int VERSION = 1;
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE contactos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        android.util.Log.v("telefonos", "actualizando la base de datos, se destruiran los datos existentes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contactos");
        onCreate(sqLiteDatabase);

    }

}

