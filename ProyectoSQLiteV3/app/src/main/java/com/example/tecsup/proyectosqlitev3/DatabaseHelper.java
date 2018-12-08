package com.example.tecsup.proyectosqlitev3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "testprueba.db";
    private static final int VERSION = 1;
    public static final String NOMBRE = "nombre";
    public static final String DIRECCION = "direccion";
    public static final String CELULAR = "celular";

    public static final String FECHA = "fecha";
    public static final String DESCRIPCION = "descripcion";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE contactos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT, celular TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE compromisos (_id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, descripcion TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        android.util.Log.v("testprueba", "actualizando la base de datos, se destruiran los datos existentes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contactos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS compromisos");
        onCreate(sqLiteDatabase);

    }

}

