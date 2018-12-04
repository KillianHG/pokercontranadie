package com.example.a41011561p.pokercontranadie;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {
    private Card[] hand = new Card[5];
    private ArrayList<Card> discards = new ArrayList<>();

    public Card[] getHand() {
        return hand;
    }

    public ArrayList<Card> getDiscards() {
        return discards;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public void setDiscards(ArrayList<Card> discards) {
        this.discards = discards;
    }
}
