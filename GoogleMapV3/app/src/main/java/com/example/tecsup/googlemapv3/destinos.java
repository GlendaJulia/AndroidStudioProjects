package com.example.tecsup.googlemapv3;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class destinos extends AppCompatActivity {

    String destino="";
    String latitud="";
    String longitud="";
    String info="";
    String UrlImagen="";
    TextView lblDestino, lblCoordenadas, lblInfo;
    ImageView imgDestino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinos);

        lblDestino = findViewById(R.id.lblDestino);
        lblCoordenadas = findViewById(R.id.lblCoordenadas);
        lblInfo = findViewById(R.id.lblInfo);
        imgDestino = findViewById(R.id.imgFoto);


        Bundle recibidos = this.getIntent().getExtras();
        if(recibidos !=null) {
            destino = getIntent().getExtras().getString("destino");
            latitud = getIntent().getExtras().getString("latitud");
            longitud = getIntent().getExtras().getString("longitud");
            UrlImagen = getIntent().getExtras().getString("url");
            info = getIntent().getExtras().getString("info");

        }
        loadImageFromUrl(UrlImagen);

        lblDestino.setText(destino);
        lblCoordenadas.setText(latitud + " , " + longitud);
        lblInfo.setText(info);

    }

    private void loadImageFromUrl(String urlImagen) {
        Picasso.with(this).load(urlImagen).placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher)//if error
                .into(imgDestino, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    public void volverLista(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
