package com.example.a41011561p.pokercontranadie;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;

public class PlayingFragment extends Fragment {
    String backCardUrl = "https://opengameart.org/sites/default/files/card%20back%20red.png";

    private View view;
    private TextView deckid;
    private String id;
    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView card4;
    private ImageView card5;

    public PlayingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playing, container, false);

        deckid = view.findViewById(R.id.deckId);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        card5 = view.findViewById(R.id.card5);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        deckid = view.findViewById(R.id.deckId);
        NewGameDataTask task = new NewGameDataTask();
        task.execute();

        deckid.setText(id);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(card1);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(card2);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(card3);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(card4);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(card5);
    }

    private class NewGameDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            PokerContraNadieAPI api = new PokerContraNadieAPI();
            return api.newDeckID();
        }
    }
}