package com.example.a41011561p.pokercontranadie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a41011561p.pokercontranadie.databinding.LvHistoryRowBinding;

import java.util.List;


public class HandAdapter extends ArrayAdapter<HistoryDB> {

    public HandAdapter(Context context, int resource, List<HistoryDB> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        HistoryDB hand = getItem(position);
        Log.w("XXXX", hand.toString());

        LvHistoryRowBinding binding = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_history_row, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);

        }
        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        binding.gameScore.setText("PARTIDA Nº: " +position);

        // Retornem la View replena per a mostrarla
        return binding.getRoot();
    }

}


