package com.example.tecsup.pasovariablesactividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class actividad_juegos extends AppCompatActivity {

    ListView lista;
    String datosactividad01="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_juegos);

        lista = (ListView)findViewById(R.id.listadejuegos);
        String[] values = new String[]{ "Dota", "Pacman", "Mario",
                "Cartas", "Ajedrez", "Ludo", "Yenga"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,values);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lista.getItemAtPosition(position);

                Intent intent = new Intent(actividad_juegos.this, MainActivity.class);
                intent.putExtra("parametro", itemValue);
                intent.putExtra("datosactividad01", datosactividad01);
                startActivity(intent);
            }
        });

        Bundle recibidos = this.getIntent().getExtras();
        if (recibidos!=null){
            datosactividad01 = getIntent().getExtras().getString("seleccionados");
        }
    }
}
