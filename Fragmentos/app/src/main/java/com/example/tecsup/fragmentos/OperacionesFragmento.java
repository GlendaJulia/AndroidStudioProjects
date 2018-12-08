package com.example.tecsup.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OperacionesFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OperacionesFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public OperacionesFragmento() {
        // Required empty public constructor
    }
    EditText txtNum1,txtNum2;
    Button btnEnviar;
    private ListView listaOperaciones;
    int num1,num2;
    Double resultado;
    //private ArrayList<String> operaciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operaciones_fragmento,container,false);
        listaOperaciones = view.findViewById(R.id.lstOperaciones);
        txtNum1 = view.findViewById(R.id.txtNum1);
        txtNum2 = view.findViewById(R.id.txtNum2);
        btnEnviar = view.findViewById(R.id.btnEnviar);



        String[] operaciones = new String[] {"Suma","Resta","Multiplicar","Dividir"};

        ArrayAdapter<String> adaptador= new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,android.R.id.text1,operaciones);
        listaOperaciones.setAdapter(adaptador);


        listaOperaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                int posicion = i;
                String operacion = (String ) listaOperaciones.getItemAtPosition(posicion);

                if(operacion =="Suma"){
                    Double num1 = Double.parseDouble(txtNum1.getText().toString());
                    Double num2 = Double.parseDouble(txtNum2.getText().toString());
                    resultado = num1+num2;
                    Toast.makeText(getActivity(),""+resultado,Toast.LENGTH_SHORT).show();
                }else if(operacion =="Resta"){
                    Double num1 = Double.parseDouble(txtNum1.getText().toString());
                    Double num2 = Double.parseDouble(txtNum2.getText().toString());
                    resultado = num1-num2;
                }else if(operacion=="Multiplicar"){
                    Double num1 = Double.parseDouble(txtNum1.getText().toString());
                    Double num2 = Double.parseDouble(txtNum2.getText().toString());
                    resultado = num1*num2;
                }else if(operacion=="Dividir"){
                    Double num1 = Double.parseDouble(txtNum1.getText().toString());
                    Double num2 = Double.parseDouble(txtNum2.getText().toString());
                    resultado = num1/num2;
                }

            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RespuestaFragmento frmRespuesta= new RespuestaFragmento();
                Bundle args = new Bundle();
                args.putString("texto",""+resultado);
                frmRespuesta.setArguments(args);
                FragmentTransaction transaccion = getActivity().getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedor2,frmRespuesta);
                transaccion.commit();
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
}