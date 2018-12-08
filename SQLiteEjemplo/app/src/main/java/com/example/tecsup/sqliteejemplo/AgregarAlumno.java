package com.example.tecsup.sqliteejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarAlumno extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtNacimiento;
    private EditText txtGrado;

    public AgregarAlumno() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtNacimiento = (EditText)findViewById(R.id.txtNacimiento);
        txtGrado = (EditText)findViewById(R.id.txtGrado);
    }

    public void Cancelar(View v) {
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        finish();
        startActivity(ListaAlumnos);
    }

    public void Grabar(View v) {
        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String fec_nac = txtNacimiento.getText().toString();
        String grado = txtGrado.getText().toString();
        insertarContacto(nombre, apellidos, fec_nac, grado);
        Toast.makeText(this, "Empleado agregado correctamente.", Toast.LENGTH_LONG).show();
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        finish();
        startActivity(ListaAlumnos);
    }

    private void insertarContacto(String xnom, String xape, String xfec, String xgra) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", xnom);
        cv.put("apellidos", xape);
        cv.put("fec_nac", xfec);
        cv.put("grado", xgra);
        db.insert("alumnos", "nombre", cv);
        db.close();
    }
}
