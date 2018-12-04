package com.example.a41011561p.pokercontranadie;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Hand implements Serializable {
    private Card[] hand = new Card[5];
    private ArrayList<Card> discards = new ArrayList<>();
    @PrimaryKey(autoGenerate = true)
    private int handId;

    public Card[] getHand() {
        return hand;
    }

    public ArrayList<Card> getDiscards() {
        return discards;
    }

    public int getHandId() {
        return handId;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public void setDiscards(ArrayList<Card> discards) {
        this.discards = discards;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }
}
