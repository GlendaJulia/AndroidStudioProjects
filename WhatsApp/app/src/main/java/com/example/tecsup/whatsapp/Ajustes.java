package com.example.tecsup.whatsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {

    ListView listaxd;

    private String lenguajeProgramacion[]=new String[]{"Java","PHP","Python","JavaScript","Ruby","C",
            "Go","Perl","Pascal"};

    private Integer[] imgid={
            R.drawable.java,
            R.drawable.php,
            R.drawable.python,
            R.drawable.javascript,
            R.drawable.ruby,
            R.drawable.c,
            R.drawable.go,
            R.drawable.perl,
            R.drawable.pascal
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        listaxd = (ListView) findViewById(R.id.listaxd);

        Lista2 adapter=new Lista2(this,lenguajeProgramacion,imgid);
        listaxd.setAdapter(adapter);
        listaxd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem= lenguajeProgramacion[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
