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
import android.widget.Switch;
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
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;
    private Switch switch4;
    private Switch switch5;
    private Button pickCards;
    private Button hideShow;
    private Button mulligan;
    private Card[] hand;
    private boolean cardsShowed;
    int nCardsMulligan;
    ArrayList<String> cardsToMulligan;
    private boolean didMulligan = false;

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
        hideShow = view.findViewById(R.id.hideShow);
        mulligan = view.findViewById(R.id.mulligan);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.pickCards:
                        hand = new Card[5];
                        NewHandDataTask nhTask = new NewHandDataTask();
                        nhTask.execute();
                        break;
                    case R.id.hideShow:
                        if (cardsShowed) {
                            hideShow.setText("HIDE");
                            showBackCards();
                            cardsShowed = false;
                        } else {
                            hideShow.setText("SHOW");
                            showCards();
                            cardsShowed = true;
                        }
                        break;
                    case R.id.mulligan:
                        cardsToMulligan = new ArrayList<>();
                        if (switch1.isChecked()) {
                            cardsToMulligan.add("0");
                           nCardsMulligan++;
                        }
                        if (switch2.isChecked()) {
                            cardsToMulligan.add("1");
                            nCardsMulligan++;
                        }
                        if (switch3.isChecked()) {
                            cardsToMulligan.add("2");
                            nCardsMulligan++;
                        }
                        if (switch4.isChecked()) {
                            cardsToMulligan.add("3");
                            nCardsMulligan++;
                        }
                        if (switch5.isChecked()) {
                            cardsToMulligan.add("4");
                            nCardsMulligan++;
                        }
                        MulliganDataTask mTask = new MulliganDataTask();
                        mTask.execute();
                        didMulligan = true;
                        mulligan.setVisibility(View.GONE);
                        switch1.setVisibility(View.GONE);
                        switch2.setVisibility(View.GONE);
                        switch3.setVisibility(View.GONE);
                        switch4.setVisibility(View.GONE);
                        switch5.setVisibility(View.GONE);
                        break;
                }
            }
        };

        pickCards.setOnClickListener(listener);
        hideShow.setOnClickListener(listener);
        mulligan.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        deckid = view.findViewById(R.id.deckId);

        NewGameDataTask ngTask = new NewGameDataTask();
        ngTask.execute();
        showBackCards();

        hideShow = view.findViewById(R.id.hideShow);
        mulligan = view.findViewById(R.id.mulligan);
        switch1 = view.findViewById(R.id.switch1);
        switch2 = view.findViewById(R.id.switch2);
        switch3 = view.findViewById(R.id.switch3);
        switch4 = view.findViewById(R.id.switch4);
        switch5 = view.findViewById(R.id.switch5);

        hideShow.setVisibility(View.INVISIBLE);
        mulligan.setVisibility(View.INVISIBLE);
        switch1.setVisibility(View.INVISIBLE);
        switch2.setVisibility(View.INVISIBLE);
        switch3.setVisibility(View.INVISIBLE);
        switch4.setVisibility(View.INVISIBLE);
        switch5.setVisibility(View.INVISIBLE);
    }

    public void showCards() {
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
        cardsShowed = true;
    }

    public void showBackCards() {
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
        cardsShowed = false;
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
            showCards();
            pickCards.setVisibility(View.GONE);
            hideShow.setVisibility(View.VISIBLE);
            mulligan.setVisibility(View.VISIBLE);
            switch1.setVisibility(View.VISIBLE);
            switch2.setVisibility(View.VISIBLE);
            switch3.setVisibility(View.VISIBLE);
            switch4.setVisibility(View.VISIBLE);
            switch5.setVisibility(View.VISIBLE);
        }
    }

    private class MulliganDataTask extends AsyncTask<Void, Void, ArrayList<Card>> {
        @Override
        protected ArrayList<Card> doInBackground(Void... voids) {
            ArrayList<Card> result;
            PokerContraNadieAPI api = new PokerContraNadieAPI();
            result = api.pickCards(id, nCardsMulligan);

            Log.d("DEBUG", result != null ? result.toString() : null);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Card> card) {
            Log.d("DEBUG", card != null ? card.toString() : null);
            for (int i = 0; i < cardsToMulligan.size(); i++) {
                hand[Integer.parseInt(cardsToMulligan.get(i))] = card.get(i);
            }
            showCards();
        }
    }




}