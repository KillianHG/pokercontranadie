package com.example.a41011561p.pokercontranadie;

import java.io.Serializable;

public class Card implements Serializable {

    private int value;
    private String suit;
    private String code;
    private String image;

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
