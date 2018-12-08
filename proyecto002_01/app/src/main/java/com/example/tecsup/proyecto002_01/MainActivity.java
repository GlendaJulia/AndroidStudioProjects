package com.example.tecsup.proyecto002_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText txtNumero;
    Button btnEval;
    RadioButton radioPar;
    RadioButton radioImpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtNumero = (EditText)findViewById(R.id.txtNro);
        final RadioButton radioPar =
                (RadioButton)findViewById(R.id.radioPar);
        final RadioButton radioImpar =
                (RadioButton)findViewById(R.id.radioImpar);
        Button btnEval = (Button)findViewById(R.id.btnEvaluar);

        btnEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cad = txtNumero.getText().toString();
                int valor = Integer.parseInt(cad);
                if( valor % 2 == 0 )
                    radioPar.setChecked(true);
                else
                    radioImpar.setChecked(true);
            }
        });

    }
}
