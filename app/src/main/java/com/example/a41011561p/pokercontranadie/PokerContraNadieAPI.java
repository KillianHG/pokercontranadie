package com.example.a41011561p.pokercontranadie;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;

public class PokerContraNadieAPI {

    private final String BASE_URL = "https://deckofcardsapi.com/api/deck";

    String newDeckID() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("new")
                .appendPath("shuffle")
                .appendPath("")
                .appendQueryParameter("deck_count", "1")
                .build();
        String url = builtUri.toString();

        Log.d("DEBUG", url != null ? url: null);


        return doCallID(url);
    }

    ArrayList<Card> pickCards(String deckId, int pickedCards) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(deckId)
                .appendPath("draw")
                .appendPath("")
                .appendQueryParameter("count", Integer.toString(pickedCards))
                .build();
        String url = builtUri.toString();
        Log.d("DEBUG", url != null ? url: null);

        return doCall(url);
    }

    private String doCallID(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processID(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Card> doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String processID (String jsonResponse){

        String id = "";
        try {
            JSONObject data = new JSONObject(jsonResponse);

            id = data.getString("deck_id");

            Log.d("ID", id != null ? id: null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return id;
    }

    private ArrayList<Card> processJson (String jsonResponse){

        ArrayList<Card> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");
            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Card card = new Card();
                card.setSuit(jsonCard.getString("suit"));
                if (jsonCard.getString("value").equals("ACE")) {
                    card.setValue(14);
                } else if (jsonCard.getString("value").equals("KING")) {
                    card.setValue(13);
                } else if (jsonCard.getString("value").equals("QUEEN")) {
                    card.setValue(12);
                } else if (jsonCard.getString("value").equals("JACK")) {
                    card.setValue(11);
                } else {
                    card.setValue(Integer.parseInt(jsonCard.getString("value")));
                }
                card.setCode(jsonCard.getString("code"));
                card.setImage(jsonCard.getString("image"));
                cards.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }




}