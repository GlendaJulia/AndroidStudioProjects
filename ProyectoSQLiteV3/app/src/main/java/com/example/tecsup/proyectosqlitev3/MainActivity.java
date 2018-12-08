package com.example.tecsup.proyectosqlitev3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }
    public void contactos_onclick(View v){
        Intent intentContactos = new Intent(v.getContext(),
                Contactos.class);
        startActivity(intentContactos);
    }

    public void compromisos_onclick(View v){
        Intent intentCompromisos = new Intent(v.getContext(),
                Compromisos.class);
        startActivity(intentCompromisos);
    }

    public void listar_contactos_onclick(View v){
        Intent intentListarContactos = new Intent(v.getContext(),
                ListarContactos.class);
        startActivity(intentListarContactos);
    }

    public void listar_compromisos_onclick(View v){
        Intent intentListarCompromisos = new Intent(v.getContext(),
                ListarCompromisos.class);
        startActivity(intentListarCompromisos);
    }

}
