package com.example.tecsup.practica01glendamerma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve,
            btnSuma, btnResta, btnMultiplicacion, btnDivision, btnAC, btnC, btnPunto, btnIgual, btnMasMenos, btnCero;
    TextView Resultado, Operador;
    double resultado;
    String operador, mostrar, reserva;
    String gle = "";
    String mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUno = (Button) findViewById(R.id.btn1);
        btnDos = (Button) findViewById(R.id.btn2);
        btnTres = (Button) findViewById(R.id.btn3);
        btnCuatro = (Button) findViewById(R.id.btn4);
        btnCinco = (Button) findViewById(R.id.btn5);
        btnSeis = (Button) findViewById(R.id.btn6);
        btnSiete = (Button) findViewById(R.id.btn7);
        btnOcho = (Button) findViewById(R.id.btn8);
        btnNueve = (Button) findViewById(R.id.btn9);
        btnCero = (Button) findViewById(R.id.btn0);
        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnMultiplicacion = (Button) findViewById(R.id.btnMultiplicacion);
        btnDivision = (Button) findViewById(R.id.btnDivision);
        btnC = (Button) findViewById(R.id.btnC);
        btnAC = (Button) findViewById(R.id.btnAC);
        Resultado = (TextView) findViewById(R.id.Etiqueta);
        Operador = (TextView) findViewById(R.id.Operador);
        btnPunto = (Button) findViewById(R.id.btnPunto);
        btnIgual = (Button) findViewById(R.id.btnIgual);
        btnMasMenos = (Button) findViewById(R.id.btnMasMenos);

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "1";
                Resultado.setText(mostrar);
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "2";
                Resultado.setText(mostrar);
            }
        });

        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "3";
                Resultado.setText(mostrar);

            }
        });

        btnCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "4";
                Resultado.setText(mostrar);
            }
        });

        btnCinco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "5";
                Resultado.setText(mostrar);
            }
        });

        btnSeis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "6";
                Resultado.setText(mostrar);
            }
        });

        btnSiete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "7";
                Resultado.setText(mostrar);
            }
        });

        btnOcho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "8";
                Resultado.setText(mostrar);
            }
        });

        btnNueve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "9";
                Resultado.setText(mostrar);
            }
        });

        btnCero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "0";
                Resultado.setText(mostrar);
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reserva = Resultado.getText().toString();
                operador = "+";
                Resultado.setText("");
                Operador.setText("+");
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reserva = Resultado.getText().toString();
                operador = "-";
                Resultado.setText("");
                Operador.setText("-");
            }
        });

        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reserva = Resultado.getText().toString();
                operador = "*";
                Resultado.setText("");
                Operador.setText("*");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reserva = Resultado.getText().toString();
                operador = "/";
                Resultado.setText("");
                Operador.setText("/");
            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                if(glenda(mostrar)==false){
                    mostrar = mostrar + ".";
                    Resultado.setText(mostrar);
                }else{
                    Toast.makeText(MainActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = "";
                Resultado.setText(mostrar);
                reserva = "";
                operador = "";
                Operador.setText("");
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = "";
                Resultado.setText("");
            }
        });

        btnMasMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                if(negativo(mostrar) == true){
                    mostrar = mostrar.substring(1, mostrar.length());
                }else{
                    mostrar = "-"+mostrar;
                }
                Resultado.setText(mostrar);
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostrar = Resultado.getText().toString();
                mostrar = mostrar + "1";
                if (operador.equals("-")) {
                    gle = Resultado.getText().toString();
                    resultado = Double.parseDouble(reserva) - Double.parseDouble(Resultado.getText().toString());
                    Resultado.setText(String.valueOf(resultado));
                    mensaje = "La resta de "+reserva+" - "+gle+" es "+ String.valueOf(resultado);
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
                if (operador.equals("+")) {
                    gle = Resultado.getText().toString();
                    resultado = Double.parseDouble(reserva) + Double.parseDouble(Resultado.getText().toString());
                    Resultado.setText(String.valueOf(resultado));
                    mensaje = "La suma de "+reserva+" + "+gle+" es "+ String.valueOf(resultado);
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
                if (operador.equals("/")) {
                    if (reserva.equals("0") || Resultado.getText().toString().equals("0")){
                        mensaje = "No se puede resolver esta operacion";
                        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    }else{
                        gle = Resultado.getText().toString();
                        resultado = Double.parseDouble(reserva) / Double.parseDouble(Resultado.getText().toString());
                        Resultado.setText(String.valueOf(resultado));
                        mensaje = "La division de "+reserva+" / "+gle+" es "+ String.valueOf(resultado);
                        Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    }
                }
                if (operador.equals("*")) {
                    gle = Resultado.getText().toString();
                    resultado = Double.parseDouble(reserva) * Double.parseDouble(Resultado.getText().toString());
                    Resultado.setText(String.valueOf(resultado));
                    mensaje = "La multiplicacion de "+reserva+" * "+gle+" es "+ String.valueOf(resultado);
                    Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean glenda(String numero){
            int intIndex = numero.indexOf(".");
        if(intIndex == -1){
            return false;
        }else{
            return true;
        }
    }
    boolean negativo(String numero){
        int intIndex = numero.indexOf("-");
        if(intIndex == -1){
            return false;
        }else{
            return true;
        }
    }
}
