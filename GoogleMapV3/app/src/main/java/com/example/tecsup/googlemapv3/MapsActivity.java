package com.example.tecsup.googlemapv3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        AdapterView.OnItemSelectedListener {

    private final LatLng Plaza = new LatLng(-16.3988084,-71.5390943);
    private GoogleMap mMap;
    private String destino="";
    Marker marcadorDestino;
    LatLng coordenada = new LatLng(0,0);
    private Spinner spnTipoMapa;
    private int tipos_mapas[] = {
            GoogleMap.MAP_TYPE_NONE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        spnTipoMapa =  findViewById(R.id.spnTipoMapa);
        spnTipoMapa.setOnItemSelectedListener(this);


        Bundle recibidos = this.getIntent().getExtras();
        if(recibidos!=null){
            destino = getIntent().getExtras().getString("destino");
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        CameraPosition camera = new CameraPosition.Builder()
                .target(Plaza)
                .zoom(15) //LIMITE DE ZOOM ES 21
                .bearing(90) //Orientacion de la camara hacia el este
                .tilt(30) // Efecto 3d en 90°
                .build();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        }

        switch (destino){
            case "plaza de armas":
                coordenada = new LatLng(-16.3988031,-71.5374435);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)

                );
                break;
            case "characato":
                coordenada = new LatLng(-16.47278,-71.4894222);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "colca":
                coordenada = new LatLng(-15.6093293,-72.0918116);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "yura":
                coordenada = new LatLng(-16.3704675,-71.5625865);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;
            case "mirador sachaca":
                coordenada = new LatLng(-16.4262814,-71.5696303);
                marcadorDestino = googleMap.addMarker(
                        new MarkerOptions()
                                .position(coordenada)
                                .title("Conoce: " + destino)
                );
                break;


            default:
                Toast.makeText(this,"No se encontro destino turistico",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,15));
        // Eventos
        mMap.setOnMarkerClickListener(this);
    }

    public void moverCamara(View view){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Plaza,10));
    }
    public void agregarMarcador(View view) {
        mMap.addMarker(new MarkerOptions().position(
                new LatLng(mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude))
                .position(new LatLng(mMap.getCameraPosition().target.latitude,
                        mMap.getCameraPosition().target.longitude))
                .title("Mi Ubicacion")
                .snippet("Lat:" + mMap.getCameraPosition().target.latitude +
                        " Lon:" + mMap.getCameraPosition().target.longitude)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icono))
                .anchor(0.5f, 0.9f)
        );

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(marcadorDestino)) {
            Intent intent = new Intent(this, destinos.class);
            intent.putExtra("destino", destino);
            intent.putExtra("latitud", (marker.getPosition().latitude + ""));
            intent.putExtra("longitud", marker.getPosition().longitude + "");
            String mensaje="";
            String urlImagen="";

            switch (destino){
                case "plaza de armas":
                    mensaje = "La plaza Mayor o plaza de Armas de Arequipa, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "y el lugar de fundación de la ciudad";
                    urlImagen="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Pileta_de_la_Plaza_mayor_de_Lima.jpg/300px-Pileta_de_la_Plaza_mayor_de_Lima.jpg";
                    break;
                case "characato":
                    mensaje = "Characato, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "y el lugar de fundación de la ciudad";
                    urlImagen="https://media-cdn.tripadvisor.com/media/photo-s/09/9b/60/0b/hotel-dos-reynas.jpg";
                    break;
                case "colca":
                    mensaje = "El cañon del colca, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "y el cañon mas grande del mundo";
                    urlImagen="https://incalake.com/galeria/admin/short-slider/arequipa/CANON-DEL-COLCA/canon-colca-dia-completo.jpg";
                    break;
                case "yura":
                    mensaje = "Yura, " +
                            "es de los lugares iconos de Arequipa" +
                            "y el lugar donde queda ubicada la empresa de cemento YURA";
                    urlImagen = "http://www.somosperu.org.pe/wp-content/uploads/2016/10/cerca-a-cataratas-de-Capua-Yura-Arequipa.jpg";
                    break;
                case "mirador sachaca":
                    mensaje = "El Mirador de Sachaca, " +
                            "es uno de los principales espacios públicos de Arequipa " +
                            "Permite una vista panoramica a los volcanes.";
                    urlImagen="https://media-cdn.tripadvisor.com/media/photo-s/03/e5/65/d8/sachaca.jpg";
                    break;


            }

            intent.putExtra("info", mensaje);
            intent.putExtra("url",urlImagen);
            startActivity(intent);
            return true;
        }
        return false;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        mMap.setMapType(tipos_mapas[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

