package com.example.tecsup.proyecto002_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    private EditText txtVal1, txtVal2, txtResp;
    private RadioButton rbtSum, rbtRes, rbtPrd, rbtDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVal1 = (EditText)findViewById(R.id.txtValor1);
        txtVal2 = (EditText)findViewById(R.id.txtValor2);
        txtResp = (EditText)findViewById(R.id.txtResultado);
        rbtSum  = (RadioButton)findViewById(R.id.radioSuma);
        rbtRes  = (RadioButton)findViewById(R.id.radioResta);
        rbtPrd  = (RadioButton)findViewById(R.id.radioProducto);
        rbtDiv  = (RadioButton)findViewById(R.id.radioDivision);

        rbtSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val1 = Double.parseDouble( txtVal1.getText().toString() );
                double val2 = Double.parseDouble( txtVal2.getText().toString() );
                double resp = val1 + val2;
                txtResp.setText( "" + resp );
            }
        });

        rbtRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val1 = Double.parseDouble( txtVal1.getText().toString() );
                double val2 = Double.parseDouble( txtVal2.getText().toString() );
                double resp = val1 - val2;
                txtResp.setText( "" + resp );
            }
        });

        rbtPrd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val1 = Double.parseDouble( txtVal1.getText().toString() );
                double val2 = Double.parseDouble( txtVal2.getText().toString() );
                double resp = val1 * val2;
                txtResp.setText( "" + resp );
            }
        });

        rbtDiv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val1 = Double.parseDouble( txtVal1.getText().toString() );
                double val2 = Double.parseDouble( txtVal2.getText().toString() );
                double resp = val1 / val2;
                txtResp.setText( "" + resp );
            }
        });


    }
}
