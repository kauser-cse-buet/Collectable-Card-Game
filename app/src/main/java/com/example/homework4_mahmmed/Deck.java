package com.example.homework4_mahmmed;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private static final int MAXIMUM_DECK_SIZE = 10;

    private int id;
    private List<Card> cardList;

    public Deck(){
        this.cardList = new ArrayList<>();
    }

    public void add(Card card){
        if (this.cardList.size() < MAXIMUM_DECK_SIZE){
            this.cardList.add(card);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public int getId() {
        return id;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
