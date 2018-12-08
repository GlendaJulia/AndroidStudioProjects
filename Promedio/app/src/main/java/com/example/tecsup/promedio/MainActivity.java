package com.example.tecsup.promedio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText P1, P2, P3, P4, PL;
    Button btnCalcular, btnLimpiar;
    TextView APR, DES, RESULTADO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        P1 = (EditText)findViewById(R.id.P1);
        P2 = (EditText)findViewById(R.id.P2);
        P3 = (EditText)findViewById(R.id.P3);
        P4 = (EditText)findViewById(R.id.P4);
        PL = (EditText)findViewById(R.id.PL);
        btnCalcular  = (Button)findViewById(R.id.btnCalcular);
        btnLimpiar  = (Button)findViewById(R.id.btnLimpiar);
        DES  = (TextView)findViewById(R.id.DES);
        APR  = (TextView)findViewById(R.id.APR);
        RESULTADO  = (TextView)findViewById(R.id.RESULTADO);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DES.setVisibility(View.INVISIBLE);
                APR.setVisibility(View.INVISIBLE);
                String pra1 = P1.getText().toString();
                int p1 = Integer.parseInt(pra1);
                String pra2 = P2.getText().toString();
                int p2 = Integer.parseInt(pra2);
                String pra3 = P3.getText().toString();
                int p3 = Integer.parseInt(pra3);
                String pra4 = P4.getText().toString();
                int p4 = Integer.parseInt(pra4);
                String lab = PL.getText().toString();
                int Lab1 = Integer.parseInt(lab);

                double promP = (p1+p2+p3+p4)/4;
                double promF = (promP*0.3)+(Lab1*0.7);

                if (promF>12.5){
                    RESULTADO.setText("Usted tiene "+promF+" de promedio: APROBADO");
                    APR.setVisibility(View.VISIBLE);
                }else{
                    RESULTADO.setText("Usted tiene "+promF+" de promedio: DESAPROBADO");
                   DES.setVisibility(View.VISIBLE);
                }
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P1.setText("");
                P2.setText("");
                P3.setText("");
                P4.setText("");
                PL.setText("");
                RESULTADO.setText("");
                DES.setVisibility(View.INVISIBLE);
                APR.setVisibility(View.INVISIBLE);
            }
        });
    }
}
