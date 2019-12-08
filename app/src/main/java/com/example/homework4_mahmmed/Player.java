package com.example.homework4_mahmmed;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int _id;
    private String name;
    private int money;
    public List<Card> unopenedCards;
    public List<Card> openedCards;
    private List<Deck> deckList;

    public static final Player[] players = new Player[]{
            new Player("kauser", 1000)
    };

    public Player(){
        this.unopenedCards = new ArrayList<>();
        this.openedCards = new ArrayList<>();
        this.deckList = new ArrayList<>();
    }

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.unopenedCards = new ArrayList<>();
        this.openedCards = new ArrayList<>();
        this.deckList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Card> getOpenedCards() {
        return openedCards;
    }

    public List<Card> getUnopenedCards() {
        return unopenedCards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setOpenedCards(List<Card> openedCards) {
        this.openedCards = openedCards;
    }

    public void setUnopenedCards(List<Card> unopenedCards) {
        this.unopenedCards = unopenedCards;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public List<Deck> getDeckList() {
        return deckList;
    }

    public void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }
}
