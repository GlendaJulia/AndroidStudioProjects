package com.example.tecsup.proyectoaudiov3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class musicas extends AppCompatActivity {

    private ListView lstMusicas;
    private List<String> item = null;
    //Acceder al dispositivo
    //private String ruta = Environment.getExternalStorageDirectory() + "/Download/";
    private String ruta2 = Environment.getExternalStorageDirectory() + "/Download/";
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicas);

        item = new ArrayList<String>();
        final List<String> item = new ArrayList<String>();

        //version sdk 23(android 6.1) para arriba hay que conceder permisos desde codigo adicionalmente al androidmanifest
        final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123;
        String state = Environment.getExternalStorageState();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);

        }

        File f = new File(ruta2);
        File[] files = f.listFiles();
        String cadena;
        String sSubCadena;

        for (int i = 0; i < files.length; i++) {
            File archivos = files[i];
            if (archivos.isDirectory()) {
                //no agregamos ningun directorio a la lista
            } else{
                cadena = archivos.toString();
                sSubCadena = cadena.substring(cadena.length()-4);
                if(sSubCadena.equals(".mp3")){
                    item.add(archivos.getName());
                }
            }
        }


        final ListView lstMusicas = findViewById(R.id.lstMusicas);
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        lstMusicas.setAdapter(fileList);

        lstMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lstMusicas.getItemAtPosition(position);
                Intent enviarMusica = new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                enviarMusica.putExtra("musica", ruta2 + itemValue);
                enviarMusica.putExtra("nombre", itemValue);
                startActivity(enviarMusica);
                finish();
            }
        });

    }
}
