package com.example.tecsup.proyectoaudiov3;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private MediaPlayer mediaplayer;
    private int playbackPosition = 0;
    private Handler mHandler = new Handler();
    SeekBar progresoMusica;
    TextView lblDuracion, lblPosicionActual;
    TextView lblNombre;
    SeekBar sk_volumen;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblNombre = findViewById(R.id.lblNombre);
        progresoMusica = findViewById(R.id.progresoMusica);
        progresoMusica.setOnSeekBarChangeListener(this);
        lblDuracion = findViewById(R.id.lblDuracion);
        lblPosicionActual = findViewById(R.id.lblPosicionActual);


        Bundle reproducir = this.getIntent().getExtras();
        if(reproducir != null){
            String musica = getIntent().getExtras().getString("musica");
            String nombre = getIntent().getExtras().getString("nombre");
            try {
                playAudio(musica);
                lblNombre.setText(nombre);
                progresoMusica.setProgress(0);
                progresoMusica.setMax(100);
                actualizarProgreso();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void killMediaPlayer(){
        if(mediaplayer!=null){
            try{
                mediaplayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        killMediaPlayer();
        super.onDestroy();
    }

    public void playLocalAudio() throws Exception{
        killMediaPlayer();
        mediaplayer = MediaPlayer.create(this,R.raw.audio2);
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaplayer.start();
    }

    public void doClic(View v){
        switch (v.getId()){
            case R.id.btnPlay:
                try{
                    if(mediaplayer==null) {
                        playLocalAudio();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btnPause:
                if(mediaplayer!=null && mediaplayer.isPlaying()){
                    playbackPosition = mediaplayer.getCurrentPosition();
                    mediaplayer.pause();
                }
                break;
            case R.id.btnReset:
                if(mediaplayer!=null && !mediaplayer.isPlaying()){
                    mediaplayer.seekTo(playbackPosition);
                    mediaplayer.start();
                }
                break;
            case R.id.btnStop:
                if(mediaplayer!=null){
                    mediaplayer.stop();
                    playbackPosition = 0;
                    mediaplayer=null;
                }
                break;
        }
        Volumen();
    }

    public void playAudio(String url) throws Exception{
        killMediaPlayer();
        String filePath = url;
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(inputStream.getFD());
        inputStream.close();
        mediaplayer.prepare();
        mediaplayer.start();
    }

    public void verLista(View v){
        Intent cargarLista = new Intent(this, musicas.class);
        startActivity(cargarLista);
    }

    private Runnable hiloActualizarProgreso = new Runnable() {
        public void run() {
            try {
                long totalDuration = mediaplayer.getDuration();
                long currentDuration = mediaplayer.getCurrentPosition();

                // Displaying Total Duration time
                lblDuracion.setText("" + Utilidades.milliSecondsToTimer(totalDuration));
                // Displaying time completed playing
                lblPosicionActual.setText("" + Utilidades.milliSecondsToTimer(currentDuration));

                // Updating progress bar
                int progress = Utilidades.getProgressPercentage(currentDuration, totalDuration);
                progresoMusica.setProgress(progress);

                // Running this thread after 1000 milliseconds
                mHandler.postDelayed(this, 1000);
            }catch (Exception e){

            }
        }
    };
    public void actualizarProgreso() {
        mHandler.postDelayed(hiloActualizarProgreso, 1000);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(hiloActualizarProgreso);
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(hiloActualizarProgreso);
        int totalDuration = mediaplayer.getDuration();
        int currentPosition = Utilidades.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mediaplayer.seekTo(currentPosition);

        // update timer progress again
        actualizarProgreso();
    }
    private void Volumen() {

        try {
            sk_volumen = (SeekBar)findViewById(R.id.volumen);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            sk_volumen.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            sk_volumen.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            sk_volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override            public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
