package com.example.tecsup.proyectosqlitev3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Compromisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromisos);
    }
    public void regresar_onclick(View v) {
        finish();
    }

    public void grabar_onclick(View v) {
        int aa = ((DatePicker)findViewById(R.id.datFecha)).getYear();
        int mm = ((DatePicker)findViewById(R.id.datFecha)).getMonth() + 1;
        int dd = ((DatePicker)findViewById(R.id.datFecha)).getDayOfMonth();
        String xfec = "" + aa + "/" + mm + "/" + dd;
        String xdes = ((EditText)findViewById(R.id.txtDescripcion)).getText().toString();
        insertarCompromiso(xfec,xdes);

        Toast.makeText(v.getContext(),"Grabando registro",
                Toast.LENGTH_SHORT).show();
    }
    private void insertarCompromiso(String xfec, String xdes) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.FECHA, xfec);
        cv.put(DatabaseHelper.DESCRIPCION, xdes);
        db.insert("compromisos", DatabaseHelper.FECHA, cv);
        db.close();
    }

}
