package com.example.tecsup.proyectosqlitev3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListarCompromisos extends AppCompatActivity {
    ListView lista;
    ArrayList<String> compromisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromisos);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM compromisos" ;

        Cursor cursor = db.rawQuery(query, null);

        ArrayAdapter<String> adaptador;
        compromisos = new ArrayList<String>();
        lista=(ListView)findViewById(R.id.txtListado);

        if (cursor.moveToFirst()) {
            do {
                String xid  = cursor.getString(0);
                String xfec = cursor.getString(1);
                String xdes = cursor.getString(2);
                compromisos.add(xid + ":" + xfec + "\n   Mensaje:" + xdes + "\n");



            } while (cursor.moveToNext());
            adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, compromisos);

            lista.setAdapter(adaptador);

            cursor.close();
        }
        db.close();

    }

    public void regresar_onclick(View v) {
        finish();
    }
}
