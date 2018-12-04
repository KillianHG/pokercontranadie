package com.example.a41011561p.pokercontranadie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class HandAdapter extends ArrayAdapter<Hand> {

    public HandAdapter(Context context, int resource, List<Hand> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Hand hand = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_history_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView gameScore = convertView.findViewById(R.id.gameScore);


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        gameScore.setText("PARTIDA Nº: " +position);

        // Retornem la View replena per a mostrarla
        return convertView;
    }

}


