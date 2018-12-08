package com.example.tecsup.sqliteejemplo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class ListaAlumnos extends AppCompatActivity {
    private ArrayList<String> alumnos;
    private ArrayAdapter<String> adaptador;
    private ListView listaAlumnos;

    public ListaAlumnos() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);
        alumnos = new ArrayList();
        listaAlumnos = (ListView)findViewById(R.id.listaAlumnos);
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM alumnos";
        Cursor cursor = db.rawQuery(query, (String[])null);
        if (cursor.moveToFirst()) {
            do {
                String xid = cursor.getString(0);
                String xnom = cursor.getString(1);
                String xape = cursor.getString(2);
                String xfec = cursor.getString(3);
                String xgra = cursor.getString(4);
                alumnos.add("id " + xid + ".\n  Empleado: " + xnom + " " + xape + "\n  Fecha de Nacimiento: " + xfec + "\n  Ocupacion: " + xgra + "\n");
            } while(cursor.moveToNext());

            adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alumnos);
            listaAlumnos.setAdapter(adaptador);
            cursor.close();
        }

        db.close();
        this.listaAlumnos.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = "";
                String valorElemento = (String)ListaAlumnos.this.listaAlumnos.getItemAtPosition(i);

                for(int j = 0; i < valorElemento.length() && valorElemento.charAt(j) != '.'; ++j) {
                    if (valorElemento.charAt(j) != 'i' && valorElemento.charAt(j) != 'd' && valorElemento.charAt(j) != ' ') {
                        id = id + valorElemento.charAt(j);
                    }
                }

                Intent InformacionAlumno = new Intent(view.getContext(), InformacionAlumno.class);
                InformacionAlumno.putExtra("id", id);
                ListaAlumnos.this.finish();
                ListaAlumnos.this.startActivity(InformacionAlumno);
            }
        });
    }

    public void Atras(View v) {
        this.finish();
    }

    public void Nuevo(View v) {
        Intent AgregarAlumno = new Intent(v.getContext(), AgregarAlumno.class);
        this.finish();
        this.startActivity(AgregarAlumno);
    }
}
