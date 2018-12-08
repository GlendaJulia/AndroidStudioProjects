package com.example.tecsup.recibirmensajes;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView lstContactos;
    private String[] elementosLista = null;
    private Map<String, ?> elementosGuardados = null;

    private Map<String, ?> eliminado = null;

    SharedPreferences datos1;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstContactos = findViewById(R.id.lstContactos);
        registerForContextMenu(lstContactos);


        final int READ_PHONE_STATE = 123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE);
        }
        final int READ_CALL_LOG = 123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, READ_CALL_LOG);
        }

        SharedPreferences datos = this.getSharedPreferences("DatosDeReceptor", Context.MODE_PRIVATE);
        elementosGuardados = datos.getAll();
        elementosLista = (String[])elementosGuardados.values().toArray(new String[elementosGuardados.size()]);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elementosLista);
        lstContactos.setAdapter(adaptador);
    }

    public void mostrarVentanaPersonalizada(final String numero){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.ventana_personalizada, null);
        dialogBuilder.setView(dialogView);

        final EditText lblMensaje = dialogView.findViewById(R.id.mensaje);

        dialogBuilder.setTitle("Mensaje a " + numero);
        dialogBuilder.setMessage("Ingrese contenido del mensaje");
        dialogBuilder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 123);
                    }
                    Intent envioSMS = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + numero));
                    envioSMS.putExtra("sms_body", lblMensaje.getText().toString());
                    startActivity(envioSMS);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void cerrar(View v){
        finish();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int seleccionlista;
        seleccionlista = info.position;
        switch (item.getItemId()) {
            case R.id.mensaje:
                //Toast.makeText(getBaseContext(), "Envio de mensaje a configurar", Toast.LENGTH_LONG).show();
                String numero1 = lstContactos.getAdapter().getItem(seleccionlista).toString();
                mostrarVentanaPersonalizada(numero1);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
