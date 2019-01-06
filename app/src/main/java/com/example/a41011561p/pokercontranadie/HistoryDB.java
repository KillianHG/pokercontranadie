package com.example.a41011561p.pokercontranadie;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class HistoryDB implements Serializable {

    private String card1;
    private String card2;
    private String card3;
    private String card4;
    private String card5;
    private int score;
    private String discardList;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public String getCard1() {
        return card1;
    }

    public String getCard2() {
        return card2;
    }

    public String getCard3() {
        return card3;
    }

    public String getCard4() {
        return card4;
    }

    public String getCard5() {
        return card5;
    }

    public int getScore() {
        return score;
    }

    public String getDiscardList() {
        return discardList;
    }

    public int getId() {
        return id;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    public void setCard4(String card4) {
        this.card4 = card4;
    }

    public void setCard5(String card5) {
        this.card5 = card5;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDiscardList(String discardList) {
        this.discardList = discardList;
    }

    public void setId(int id) {
        this.id = id;
    }
}
