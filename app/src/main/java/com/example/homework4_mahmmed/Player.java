package com.example.homework4_mahmmed;

import java.util.List;

public class Player {
    private String playerName;
    private int money;
    private List<Card> unopenedCards;
    private List<Card> openedCards;

    public Player(){

    }

    public String getPlayerName() {
        return playerName;
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

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
}
