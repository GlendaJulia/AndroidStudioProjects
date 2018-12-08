package com.example.tecsup.recibirmensajes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sms extends AppCompatActivity{

    TextView mensaje, numero;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        numero = (TextView) findViewById(R.id.contacto);
        mensaje = (TextView) findViewById(R.id.mensaje);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String address = extras.getString("MessageNumber");
            String message = extras.getString("Message");
            numero.setText(address);
            mensaje.setText("Mensaje: "+ message);
        }

    }

    public void guardarNumero(View v){
        // se apertura archivo de preferencias, para guardar datos en memoria
        SharedPreferences datos = getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datos.edit();
        String guardado = numero.getText().toString();
        editor.putString(guardado, numero.getText().toString());
        editor.commit();


        Intent mostrarNumeros = new Intent(this,MainActivity.class);
        startActivity(mostrarNumeros);
        finish();
    }
}
