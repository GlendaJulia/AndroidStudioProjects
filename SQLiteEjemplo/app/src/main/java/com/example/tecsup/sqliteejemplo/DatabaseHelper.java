package com.example.tecsup.sqliteejemplo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "testprueba.db";
    private static final int VERSION = 1;
    public static final String NOMBRE = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String FEC_NAC = "fec_nac";
    public static final String GRADO = "grado";

    public DatabaseHelper(Context context) {
        super(context, "testprueba.db", (CursorFactory)null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE alumnos (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellidos TEXT, fec_nac TEXT, grado TEXT);");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.v("testprueba", "actualizando la base de datos, se destruiran los datos existentes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS alumnos");
        this.onCreate(sqLiteDatabase);
    }
}
