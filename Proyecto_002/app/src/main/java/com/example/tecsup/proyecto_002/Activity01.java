package com.example.tecsup.proyecto_002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Activity01 extends AppCompatActivity {

    EditText txtusuario;
    EditText txtclave;
    TextView lblver;
    Button cmdok;
    ToggleButton tglonoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        txtusuario=(EditText) findViewById(R.id.txtusuario);
        txtclave=(EditText) findViewById(R.id.txtclave);
        tglonoff=(ToggleButton) findViewById(R.id.tglonoff);
        lblver=(TextView) findViewById(R.id.lblver);
    }

    void funcionVer(View v){
        String usuario=txtusuario.getText().toString();
        String clave=txtclave.getText().toString();
        lblver.setText("");
        if(tglonoff.isChecked()){
            lblver.setText(""+clave);
        }else{
            lblver.setText(usuario+" no tiene permisos para ver clave");
        }
    }
}
