package com.example.a41011561p.pokercontranadie;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.a41011561p.pokercontranadie.databinding.FragmentPlayingBinding;

import java.util.ArrayList;

public class PlayingFragment extends Fragment {
    String backCardUrl = "https://opengameart.org/sites/default/files/card%20back%20red.png";

    private View view;
    private String id = "";
    private FragmentPlayingBinding binding;
    private Hand hand;
    private boolean cardsShowed;
    int nCardsMulligan;
    ArrayList<String> cardsToMulligan;
    private boolean didMulligan = false;
    HistoryDB history = new HistoryDB();
    private HandViewModel model;

    public PlayingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayingBinding.inflate(inflater);
        view = binding.getRoot();

        model = ViewModelProviders.of(this).get(HandViewModel.class);

        binding.score.setVisibility(View.GONE);
        binding.exit.setVisibility(View.GONE);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.pickCards:
                        if (!didMulligan) {
                            hand = new Hand();
                            NewHandDataTask nhTask = new NewHandDataTask();
                            nhTask.execute();
                        } else {
                            showCards();
                            binding.hideShow.setVisibility(View.GONE);
                            binding.pickCards.setVisibility(View.GONE);
                            binding.score.setText("EXAMPLE SCORE");
                            binding.score.setVisibility(View.VISIBLE);
                            binding.exit.setVisibility(View.VISIBLE);
                        }
                        break;
                    case R.id.hideShow:
                        if (cardsShowed) {
                            binding.hideShow.setText("HIDE");
                            showBackCards();
                            cardsShowed = false;
                        } else {
                            binding.hideShow.setText("SHOW");
                            showCards();
                            cardsShowed = true;
                        }
                        break;
                    case R.id.mulligan:
                        cardsToMulligan = new ArrayList<>();
                        if (binding.switch1.isChecked()) {
                            cardsToMulligan.add("0");
                            nCardsMulligan++;
                        }
                        if (binding.switch2.isChecked()) {
                            cardsToMulligan.add("1");
                            nCardsMulligan++;
                        }
                        if (binding.switch3.isChecked()) {
                            cardsToMulligan.add("2");
                            nCardsMulligan++;
                        }
                        if (binding.switch4.isChecked()) {
                            cardsToMulligan.add("3");
                            nCardsMulligan++;
                        }
                        if (binding.switch5.isChecked()) {
                            cardsToMulligan.add("4");
                            nCardsMulligan++;
                        }
                        MulliganDataTask mTask = new MulliganDataTask();
                        mTask.execute();
                        didMulligan = true;
                        binding.mulligan.setVisibility(View.GONE);
                        binding.switch1.setVisibility(View.GONE);
                        binding.switch2.setVisibility(View.GONE);
                        binding.switch3.setVisibility(View.GONE);
                        binding.switch4.setVisibility(View.GONE);
                        binding.switch5.setVisibility(View.GONE);
                        binding.pickCards.setVisibility(View.VISIBLE);
                        binding.pickCards.setText("END");

                        history.setCard1(hand.getHand()[0].getImage());
                        history.setCard2(hand.getHand()[1].getImage());
                        history.setCard3(hand.getHand()[2].getImage());
                        history.setCard4(hand.getHand()[3].getImage());
                        history.setCard5(hand.getHand()[4].getImage());
                        history.setScore(hand.getHand()[0].getValue());

                        model.addHand(history);

                        break;
                    case R.id.exit:
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                        break;
                }
            }
        };

        binding.pickCards.setOnClickListener(listener);
        binding.hideShow.setOnClickListener(listener);
        binding.mulligan.setOnClickListener(listener);
        binding.exit.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        NewGameDataTask ngTask = new NewGameDataTask();
        ngTask.execute();
        showBackCards();

        binding.hideShow.setVisibility(View.INVISIBLE);
        binding.mulligan.setVisibility(View.INVISIBLE);
        binding.switch1.setVisibility(View.INVISIBLE);
        binding.switch2.setVisibility(View.INVISIBLE);
        binding.switch3.setVisibility(View.INVISIBLE);
        binding.switch4.setVisibility(View.INVISIBLE);
        binding.switch5.setVisibility(View.INVISIBLE);
    }

    public void showCards() {
        Glide.with(getContext())
                .load(hand.getHand()[0].getImage()
                ).into(binding.card1);
        Glide.with(getContext())
                .load(hand.getHand()[1].getImage()
                ).into(binding.card2);
        Glide.with(getContext())
                .load(hand.getHand()[2].getImage()
                ).into(binding.card3);
        Glide.with(getContext())
                .load(hand.getHand()[3].getImage()
                ).into(binding.card4);
        Glide.with(getContext())
                .load(hand.getHand()[4].getImage()
                ).into(binding.card5);
        cardsShowed = true;
    }

    public void showBackCards() {
        Glide.with(getContext())
                .load(backCardUrl
                ).into(binding.card1);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(binding.card2);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(binding.card3);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(binding.card4);
        Glide.with(getContext())
                .load(backCardUrl
                ).into(binding.card5);
        cardsShowed = false;
    }

    public void getScore(Hand hand) {

        for (int i = 0; i < 5; i++) {
            for (int j = i+1; j < 5; j++) {
                if (hand.getHand()[i].getValue() > hand.getHand()[j].getValue()) {

                }
            }
        }
        
        /*int compareTo (Hand that) {
            for (int x=0; x<6; x++) //cycle through values
            {
                if (this.value[x]>that.value[x])
                    return 1;
                else if (this.value[x]<that.value[x])
                    return -1;
            }
            return 0; //if hands are equal
        }*/

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
            binding.deckId.setText("GAME ID: " + id);
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
            for (int i = 0; i < 5; i++) {
                hand.getHand()[i] = cards.get(i);
            }
            showCards();
            binding.pickCards.setVisibility(View.GONE);
            binding.hideShow.setVisibility(View.VISIBLE);
            binding.mulligan.setVisibility(View.VISIBLE);
            binding.switch1.setVisibility(View.VISIBLE);
            binding.switch2.setVisibility(View.VISIBLE);
            binding.switch3.setVisibility(View.VISIBLE);
            binding.switch4.setVisibility(View.VISIBLE);
            binding.switch5.setVisibility(View.VISIBLE);
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
                hand.getDiscards().add(hand.getHand()[Integer.parseInt(cardsToMulligan.get(i))]);
                hand.getHand()[Integer.parseInt(cardsToMulligan.get(i))] = card.get(i);
            }
            showCards();
        }
    }
}