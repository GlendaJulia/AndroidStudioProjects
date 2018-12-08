package com.example.tecsup.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText P1, P2, P3, P4, Lab;
    Button btnCalcular;
    TextView x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        P1 = (EditText)findViewById(R.id.P1);
        P2 = (EditText)findViewById(R.id.P2);
        P3 = (EditText)findViewById(R.id.P3);
        P4 = (EditText)findViewById(R.id.P4);
        Lab = (EditText)findViewById(R.id.Lab);
        btnCalcular  = (Button)findViewById(R.id.btnCalcular);
        x1  = (TextView)findViewById(R.id.x1);
        x2  = (TextView)findViewById(R.id.x2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pra1 = P1.getText().toString();
                int p1 = Integer.parseInt(pra1);
                String pra2 = P2.getText().toString();
                int p2 = Integer.parseInt(pra2);
                String pra3 = P3.getText().toString();
                int p3 = Integer.parseInt(pra3);
                String pra4 = P4.getText().toString();
                int p4 = Integer.parseInt(pra4);
                String lab = Lab.getText().toString();
                int Lab1 = Integer.parseInt(lab);

                double promP = (p1+p2+p3+p4)/4;
                double promF = (promP*0.3)+(Lab1*0.7);

                if (promF>12.5){
                    x2.setVisibility(View.VISIBLE);
                }else{
                    x1.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
