package com.example.tecsup.fragmentos;



import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicasFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MusicasFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public MusicasFragmento() {
        // Required empty public constructor
    }

    private ListView lstMusicas;
    private List<String> item = null;
    private String ruta = Environment.getExternalStorageDirectory() + "/Download/";

    private MediaPlayer mediaplayer;
    private int playbackPosition = 0;

    ListView lista;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musicas_fragmento, container, false);



        lista = view.findViewById(R.id.lstMusicas);

        item = new ArrayList<String>();
        final List<String> item = new ArrayList<String>();

        //version sdk 23(android 6.1) para arriba hay que conceder permisos desde codigo adicionalmente al androidmanifest
        final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123;
        String state = Environment.getExternalStorageState();
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }

        File f = new File(ruta);
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            File archivos = files[i];
            if (archivos.isDirectory()) {
                item.add(archivos.getName());
                //no agregamos ningun directorio a la lista
            }else{
                String ultimo = archivos.getName().toString();
                ultimo = ultimo.substring(ultimo.length() - 4);
                if(ultimo.equals(".mp3")){
                    item.add(archivos.getName());
                }else{
                    //nada
                }

            }

        }


        final ListView lstMusicas = view.findViewById(R.id.lstMusicas);
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, item);
        lstMusicas.setAdapter(fileList);

        lstMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                int itemPosition     = position;
                String  itemValue    = (String) lstMusicas.getItemAtPosition(position);

                String musi = ruta + itemValue;

                try {
                    playAudio(musi);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });




        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Metodos
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

    private void killMediaPlayer(){
        if(mediaplayer!=null){
            try{
                mediaplayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
