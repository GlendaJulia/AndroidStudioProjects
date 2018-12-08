package com.example.tecsup.proyectousomenusv3;


import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class actividad01 extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    ListView lista;
    ListView listalateral;
    DrawerLayout layout_lateral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad01);
        toolbar = findViewById(R.id.mi_toolbar);
        listalateral = findViewById(R.id.lista_lateral);
        layout_lateral = findViewById(R.id.layout_lateral);

        String[] opciones_menu_lateral = {"Acerca de... ", "Salir"};
        ArrayAdapter<String> adapter_lateral = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                opciones_menu_lateral);
        listalateral.setAdapter(adapter_lateral);

        listalateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                int posicion = i;
                String valorElemento = (String) listalateral.getItemAtPosition(posicion);
                Toast.makeText(getApplicationContext(), "Elemento" +
                valorElemento + " esta en la posicion "+posicion,
                        Toast.LENGTH_LONG).show();
            }
        });

        lista = (ListView) findViewById(R.id.lista);
        String[] values = new String[20];
        for (int i=0; i<20; i++){
            values[i] = "Contacto" + i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,
                values);
        lista.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerForContextMenu(lista);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuprincipal){
        getMenuInflater().inflate(R.menu.menuprincipal,menuprincipal);
        return true;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menucontextual, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){
        int seleccionlista;
        if(v.getId()==R.id.lista){
            seleccionlista = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            menucontextual.setHeaderTitle(lista.getAdapter().getItem(seleccionlista).toString());
            Toast.makeText(getBaseContext(),"Elegiste el contacto: "+
            lista.getAdapter().getItem(seleccionlista).toString(),Toast.LENGTH_SHORT).show();
            this.getMenuInflater().inflate(R.menu.menu_contextual,menucontextual);
        }

        super.onCreateContextMenu(menucontextual, v, menuInfo);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_contextual,menucontextual);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menu_elegido){
        int id = menu_elegido.getItemId();

        if(id==android.R.id.home) {
            layout_lateral.openDrawer(GravityCompat.START);
            return true;
        }
        if(id==R.id.configuracion){
            Intent llamaractividad = new Intent(getApplicationContext(),actividad_configuracion.class);
            startActivity(llamaractividad);
            return  true;
        }
        if(id==R.id.informacion){
            Intent llamaractividad = new Intent(getApplicationContext(),actividad_informacion.class);
            startActivity(llamaractividad);
            return  true;
        }
        return super.onOptionsItemSelected(menu_elegido);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.nombres:
                Toast.makeText(getBaseContext(),"Elegiste Nombres", Toast.LENGTH_LONG).show();
                return true;
            case R.id.semestre:
                Toast.makeText(getBaseContext(),"Elegiste Semestre", Toast.LENGTH_LONG).show();
                return true;
            case R.id.email:
                Toast.makeText(getBaseContext(),"Elegiste Email", Toast.LENGTH_LONG).show();
                return true;
            case R.id.carrera:
                Toast.makeText(getBaseContext(),"Elegiste Carrera", Toast.LENGTH_LONG).show();
                return true;

                default:
                    return super.onContextItemSelected(item);
        }
    }

}
