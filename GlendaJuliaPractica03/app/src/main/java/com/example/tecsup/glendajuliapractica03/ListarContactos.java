package com.example.tecsup.glendajuliapractica03;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarContactos extends AppCompatActivity {

    ListView lista;
    ArrayList<String> contactos;
    EditText mensajito;
    String smsjaja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);

        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM contactos";

        Cursor cursor = db.rawQuery(query, null);

        ArrayAdapter<String> adaptador;
        contactos = new ArrayList<String>();
        lista = (ListView) findViewById(R.id.txtListado);


        if (cursor.moveToFirst()) {
            do {
                String xid = cursor.getString(0);
                String xnombre = cursor.getString(1);
                String xtelefono = cursor.getString(2);
                contactos.add(xtelefono);


            } while (cursor.moveToNext());
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactos);

            lista.setAdapter(adaptador);

            cursor.close();
        }
        db.close();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lista.getItemAtPosition(position);
                mensajito = (EditText) findViewById(R.id.txtMensajito);
                smsjaja = mensajito.getText().toString();

                try{
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "No se tiene permiso", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(ListarContactos.this, new String[]{Manifest.permission.SEND_SMS}, 255);
                    }else {
                        Log.i("Mensaje","Se tiene permiso");
                    }
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(itemValue, null, smsjaja, null,null);
                    Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();
                }

                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Mensaje no enviado, datos incorrectos." + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
