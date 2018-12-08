package com.example.tecsup.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2, operador;
    Button Operar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        operador = (EditText) findViewById(R.id.operador);
        Operar = (Button) findViewById(R.id.Operar);

        Operar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle parametros = this.get;
                if(parametros != null) {
                    String datos = parametros.getString("datos");
                    tv2.setText(datos);}

            }
        });
    }
}
