package com.example.tecsup.proyectosqlitev3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Contactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
    }
    public void regresar_onclick(View v) {
        finish();
    }

    public void grabar_onclick(View v) {
        String xnom = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        String xdir = ((EditText)findViewById(R.id.txtDireccion)).getText().toString();
        String xcel = ((EditText)findViewById(R.id.txtCelular)).getText().toString();
        insertarContacto(xnom,xdir,xcel);
        Toast.makeText(v.getContext(),"Grabando registro",Toast.LENGTH_SHORT).show();

    }

    private void insertarContacto(String xnom, String xdir, String xcel) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NOMBRE, xnom);
        cv.put(DatabaseHelper.DIRECCION, xdir);
        cv.put(DatabaseHelper.CELULAR, xcel);
        db.insert("contactos", DatabaseHelper.NOMBRE, cv);
        db.close();
    }


}
