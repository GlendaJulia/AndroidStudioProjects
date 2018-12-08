package com.example.tecsup.whatsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Lista2 extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] integers;

    public Lista2(Activity context, String[] itemname, Integer[] integers) {
        super(context, R.layout.lista2, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.integers=integers;
    }

    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lista2,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textoo);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagen);

        txtTitle.setText(itemname[posicion]);
        imageView.setImageResource(integers[posicion]);

        return rowView;
    }


}
