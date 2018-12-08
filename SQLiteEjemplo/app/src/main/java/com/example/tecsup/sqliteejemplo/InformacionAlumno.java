package com.example.tecsup.sqliteejemplo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InformacionAlumno extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtNacimiento;
    private EditText txtGrado;
    private Button btnGuardar;
    private Button btnCancelar;
    private Button btnEditar;
    private Button btnEliminar;
    public String id;
    public String NombreEdit;
    public String ApellidosEdit;
    public String NacimientoEdit;
    public String GradoEdit;

    public InformacionAlumno() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_alumno);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtNacimiento = (EditText)findViewById(R.id.txtNacimiento);
        txtGrado = (EditText)findViewById(R.id.txtGrado);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnEditar = (Button)findViewById(R.id.btnEditar);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        id = getIntent().getStringExtra("id");
        cargarInformacion(id);
    }

    private void cargarInformacion(String id) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        String query = "Select * FROM alumnos WHERE _id=" + id;
        Cursor cursor = db.rawQuery(query, (String[])null);
        if (cursor.moveToFirst()) {
            String xnom = cursor.getString(1);
            String xape = cursor.getString(2);
            String xfec = cursor.getString(3);
            String xgra = cursor.getString(4);
            txtNombre.setText(xnom);
            txtApellidos.setText(xape);
            txtNacimiento.setText(xfec);
            txtGrado.setText(xgra);
            cursor.close();
        }

        db.close();
    }

    public void habilitarEdicion(View v) {
        txtNombre.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtGrado.setEnabled(true);
        txtNacimiento.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void Cancelar(View v) {
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtGrado.setEnabled(false);
        txtNacimiento.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        cargarInformacion(id);
    }

    public void Volver(View v) {
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        finish();
        startActivity(ListaAlumnos);
    }

    public void Editar(View v) {
        NombreEdit = txtNombre.getText().toString();
        ApellidosEdit = txtApellidos.getText().toString();
        NacimientoEdit = txtNacimiento.getText().toString();
        GradoEdit = txtGrado.getText().toString();
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nombre", NombreEdit);
        cv.put("apellidos", ApellidosEdit);
        cv.put("fec_nac", NacimientoEdit);
        cv.put("grado", GradoEdit);
        db.update("alumnos", cv, "_id=" + id, (String[])null);
        db.close();
        Toast.makeText(this, "Información modificada correctamente.", 0).show();
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        finish();
        startActivity(ListaAlumnos);
    }

    public void Eliminar(View v) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.delete("alumnos", "_id=" + id, (String[])null);
        db.close();
        Toast.makeText(this, "Empleado eliminado correctamente.", 1).show();
        Intent ListaAlumnos = new Intent(v.getContext(), ListaAlumnos.class);
        finish();
        startActivity(ListaAlumnos);
    }
}
