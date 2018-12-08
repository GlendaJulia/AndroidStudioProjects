package com.example.tecsup.proyectoactividadesintents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class actividad01 extends AppCompatActivity {

    TextView estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);
        estado = (TextView) findViewById(R.id.lblestado);

        estado.setText("Estas en el metodo onCreate");
        Toast.makeText(this, "Estas en el metodo onCreate", Toast.LENGTH_SHORT).show();

    }
    @Override protected void onStart(){
        super.onStart();
        estado.setText("Estas en el metodo onStart");
        Toast.makeText(this, "Estas en el metodo onStart", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onResume(){
        super.onResume();
        estado.setText("Estas en el metodo onResume");
        Toast.makeText(this, "Estas en el metodo onResume", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onPause(){
        estado.setText("Estas en el metodo onPause");
        Toast.makeText(this, "Estas en el metodo onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override protected void onStop(){
        estado.setText("Estas en el metodo onStop");
        Toast.makeText(this, "Estas en el metodo onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
    @Override protected void onRestart(){
        super.onRestart();
        estado.setText("Estas en el metodo onRestart");
        Toast.makeText(this, "Estas en el metodo onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onDestroy(){
        estado.setText("Estas en el metodo onDestroy");
        Toast.makeText(this, "Estas en el metodo onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
