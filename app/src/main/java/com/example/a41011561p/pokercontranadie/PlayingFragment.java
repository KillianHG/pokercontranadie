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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlayingFragment extends Fragment {
    String backCardUrl = "https://opengameart.org/sites/default/files/card%20back%20red.png";

    private View view;
    private TextView deckid;
    private String id = "";
    private ImageView card1;
    private ImageView card2;
    private ImageView card3;
    private ImageView card4;
    private ImageView card5;
    private Button pickCards;
    private Card[] hand;

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
        pickCards = view.findViewById(R.id.pickCards);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.pickCards:
                        hand = new Card[5];
                        NewHandDataTask task = new NewHandDataTask();
                        task.execute();
                        break;
                }
            }
        };

        pickCards.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        deckid = view.findViewById(R.id.deckId);
        NewGameDataTask task = new NewGameDataTask();
        task.execute();
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
            String result;
            PokerContraNadieAPI api = new PokerContraNadieAPI();
            result = api.newDeckID();
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            id = result;
            deckid.setText("GAME ID: " + id);
        }
    }

    private class NewHandDataTask extends AsyncTask<Void, Void, ArrayList<Card>> {
        @Override
        protected ArrayList<Card> doInBackground(Void... voids) {
            ArrayList<Card> result;
            PokerContraNadieAPI api = new PokerContraNadieAPI();
            result = api.pickCards(id, 5);

            Log.d("DEBUG", result != null ? result.toString() : null);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> cards) {
            for (int i = 0; i < hand.length; i++) {
                hand[i] = cards.get(i);
            }
            Glide.with(getContext())
                    .load(hand[0].getImage()
                    ).into(card1);
            Glide.with(getContext())
                    .load(hand[1].getImage()
                    ).into(card2);
            Glide.with(getContext())
                    .load(hand[2].getImage()
                    ).into(card3);
            Glide.with(getContext())
                    .load(hand[3].getImage()
                    ).into(card4);
            Glide.with(getContext())
                    .load(hand[4].getImage()
                    ).into(card5);
        }
    }


}