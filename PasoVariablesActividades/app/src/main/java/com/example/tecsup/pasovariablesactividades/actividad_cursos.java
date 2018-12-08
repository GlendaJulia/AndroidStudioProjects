package com.example.tecsup.pasovariablesactividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class actividad_cursos extends AppCompatActivity {

    ListView lista;
    String datosactividad01="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_cursos);

        lista = (ListView)findViewById(R.id.listadecursos);
        String[] values = new String[]{ "Desarrollo de Apps Web", "Programacion en Moviles", "Base de Datos",
                "Sistemas Operativos", "Diseño de Interfaces", "Ing. de Requerimientos", "Tecnologias Emergentes"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,values);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) lista.getItemAtPosition(position);

                Intent intent = new Intent(actividad_cursos.this, MainActivity.class);
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
