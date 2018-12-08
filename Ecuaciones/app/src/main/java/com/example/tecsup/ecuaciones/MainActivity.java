package com.example.tecsup.ecuaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ValorA, ValorB, ValorC;
    Button btnCalcular;
    TextView x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ValorA = (EditText)findViewById(R.id.ValorA);
        ValorB = (EditText)findViewById(R.id.ValorB);
        ValorC = (EditText)findViewById(R.id.ValorC);
        btnCalcular  = (Button)findViewById(R.id.btnCalcular);
        x1  = (TextView)findViewById(R.id.x1);
        x2  = (TextView)findViewById(R.id.x2);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa = ValorA.getText().toString();
                int vA = Integer.parseInt(aa);
                String bb = ValorB.getText().toString();
                int vB = Integer.parseInt(bb);
                String cc = ValorC.getText().toString();
                int vC = Integer.parseInt(cc);

                double X1 = (((-1)*vB)-(Math.pow((Math.pow(vB,2))-(4*vA*vC),0.5)))/(2*vA);
                double X2 = ((-1)*vB)+(Math.pow((Math.pow(vB,2))-(4*vA*vC),0.5))/(2*vA);
                double c3 = (Math.pow(80,0.5));


                x1.setText("X1 = "+X1);
                x2.setText("X2 = "+X2);

            }
        });

    }
}
