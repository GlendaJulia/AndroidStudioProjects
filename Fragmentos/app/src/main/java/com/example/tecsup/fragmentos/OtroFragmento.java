package com.example.tecsup.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OtroFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OtroFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public OtroFragmento() {
        // Required empty public constructor
    }

    Button btnFragmento1, btnFragmento2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_otro_fragmento,container,false);
        btnFragmento1 = view.findViewById(R.id.btnFragmento1);
        btnFragmento2 = view.findViewById(R.id.btnFragmento2);

        btnFragmento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragmento1 fragmento1 = new Fragmento1();
                FragmentTransaction transaccion = getActivity().getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedor,fragmento1);
                transaccion.commit();
            }
        });
        btnFragmento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragmento2 fragmento2 = new Fragmento2();
                FragmentTransaction transaccion = getActivity().getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.contenedor,fragmento2);
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
