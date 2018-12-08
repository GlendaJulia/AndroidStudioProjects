package com.example.tecsup.glendajuliapractica03;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void regresar_onclick(View v) {
        finish();
    }

    public void grabar_onclick(View v) {
        String xnom = ((EditText)findViewById(R.id.txtNombre)).getText().toString();
        String xtel = ((EditText)findViewById(R.id.txtTelefono)).getText().toString();
        insertarContacto(xnom,xtel);

        //AGREGAR NOTIFICACION

        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder1 =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.imgnotificacion)
                        .setContentTitle("Registro Insertado")
                        .setSound(alarm)
                        .setContentText("Abrir lista de Contactos")
                        .setOngoing(true)
                        .setAutoCancel(true);

        Intent notificacionIntent = new Intent(this, ListarContactos.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificacionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder1.setContentIntent(contentIntent);
        long[] vibraPattern = { 1000, 1000, 1000, 1000,1000,1000 };
        builder1.setVibrate(vibraPattern);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder1.build());
    }
    private void insertarContacto(String xnom, String xtel) {
        DatabaseHelper dbh = new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.NOMBRE, xnom);
        cv.put(DatabaseHelper.TELEFONO, xtel);
        db.insert("contactos", DatabaseHelper.NOMBRE, cv);
        db.close();
    }

    public void agregarNotificacion(View v){


    }


}
