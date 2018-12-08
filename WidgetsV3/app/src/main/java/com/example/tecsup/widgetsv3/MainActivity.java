package com.example.tecsup.widgetsv3;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private int widgetId = 0;
    private Button btnAceptar, btnCancelar;
    private EditText txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMensaje = findViewById(R.id.txtEnviar);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        Intent recibidowidget = getIntent();
        Bundle parametros = recibidowidget.getExtras();
        if(parametros != null){
            widgetId = parametros.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        setResult(RESULT_CANCELED);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences datos = getSharedPreferences("DatosWidget", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = datos.edit();
                editor.putString("mensaje", txtMensaje.getText().toString());
                editor.commit();

                AppWidgetManager notificarwidget = AppWidgetManager.getInstance(MainActivity.this);
                mi_widget.actualizarWidget(MainActivity.this,notificarwidget,widgetId);

                Intent resultado = new Intent();
                resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
                setResult(RESULT_OK,resultado);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
