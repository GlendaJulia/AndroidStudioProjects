package com.example.tecsup.sqliteejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ListaAlumnos(View v){
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        startActivity(ListaAlumnos);
    }

    public void Salir(View v){
        finish();
    }
}
