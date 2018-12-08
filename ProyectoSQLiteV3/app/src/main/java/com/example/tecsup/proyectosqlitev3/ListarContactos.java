package com.example.tecsup.proyectosqlitev3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListarContactos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM contactos" ;

        Cursor cursor = db.rawQuery(query, null);
        TextView tv = findViewById(R.id.txtListado);
        String cad = "";
        if (cursor.moveToFirst()) {
            do {
                String xid  = cursor.getString(0);
                String xnom = cursor.getString(1);
                String xdir = cursor.getString(2);
                String xcel = cursor.getString(3);
                cad += xid + ":" + xnom + "\n   Dir:" + xdir + "\n   Fon:" + xcel + "\n";
            } while (cursor.moveToNext());
            tv.setText(cad);
            cursor.close();
        }
        db.close();
    }
    public void regresar_onclick(View v) {
        finish();
    }
}
