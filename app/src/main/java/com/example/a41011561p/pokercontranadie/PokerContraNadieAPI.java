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

    private String doCallID(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processID(JsonResponse);
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

                /*Cartas carta = new Cartas();
                carta.setName(jsonCard.getString("name"));
                carta.setColors(jsonCard.getString("colors"));
                carta.setRarity(jsonCard.getString("rarity"));
                carta.setImageUrl(jsonCard.getString("imageUrl"));
                carta.setMultiverseid(jsonCard.getInt("multiverseid"));
                cartas.add(carta);*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }




}