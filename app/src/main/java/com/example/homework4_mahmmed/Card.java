package com.example.homework4_mahmmed;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Card {
    private int cardId;
    private String cardName;
    private int cardPrice;
    private int cardImageResourceId;

    public static final Card[] cards = new Card[]{
            new Card("2 of hearts", 20, R.drawable.hearts_2),
            new Card("3 of hearts", 30, R.drawable.hearts_3),
            new Card("4 of hearts", 40, R.drawable.hearts_4),
            new Card("5 of hearts", 50, R.drawable.hearts_5),
            new Card("6 of hearts", 60, R.drawable.hearts_6),
            new Card("7 of hearts", 70, R.drawable.hearts_7),
            new Card("8 of hearts", 80, R.drawable.hearts_8),
            new Card("9 of hearts", 90, R.drawable.hearts_9),
            new Card("10 of hearts", 100, R.drawable.hearts_10),
            new Card("Jack of hearts", 120, R.drawable.hearts_jack),
            new Card("Queen of hearts", 130, R.drawable.hearts_queen),
            new Card("King of hearts", 140, R.drawable.hearts_king),
            new Card("Ace of hearts", 110, R.drawable.hearts_ace),
            new Card("2 of spades", 20, R.drawable.spades_2),
            new Card("3 of spades", 30, R.drawable.spades_3),
            new Card("4 of spades", 40, R.drawable.spades_4),
            new Card("5 of spades", 50, R.drawable.spades_5),
            new Card("6 of spades", 60, R.drawable.spades_6),
            new Card("7 of spades", 70, R.drawable.spades_7),
            new Card("8 of spades", 80, R.drawable.spades_8),
            new Card("9 of spades", 90, R.drawable.spades_9),
            new Card("10 of spades", 100, R.drawable.spades_10),
            new Card("Jack of spades", 120, R.drawable.spades_jack),
            new Card("Queen of spades", 130, R.drawable.spades_queen),
            new Card("King of spades", 140, R.drawable.spades_king),
            new Card("Ace of spades", 110, R.drawable.spades_ace),
            new Card("2 of clubs", 20, R.drawable.clubs_2),
            new Card("3 of clubs", 30, R.drawable.clubs_3),
            new Card("4 of clubs", 40, R.drawable.clubs_4),
            new Card("5 of clubs", 50, R.drawable.clubs_5),
            new Card("6 of clubs", 60, R.drawable.clubs_6),
            new Card("7 of clubs", 70, R.drawable.clubs_7),
            new Card("8 of clubs", 80, R.drawable.clubs_8),
            new Card("9 of clubs", 90, R.drawable.clubs_9),
            new Card("10 of clubs", 100, R.drawable.clubs_10),
            new Card("Jack of clubs", 120, R.drawable.clubs_jack),
            new Card("Queen of clubs", 130, R.drawable.clubs_queen),
            new Card("King of clubs", 140, R.drawable.clubs_king),
            new Card("Ace of clubs", 110, R.drawable.clubs_ace),
            new Card("2 of diamonds", 20, R.drawable.diamonds_2),
            new Card("3 of diamonds", 30, R.drawable.diamonds_3),
            new Card("4 of diamonds", 40, R.drawable.diamonds_4),
            new Card("5 of diamonds", 50, R.drawable.diamonds_5),
            new Card("6 of diamonds", 60, R.drawable.diamonds_6),
            new Card("7 of diamonds", 70, R.drawable.diamonds_7),
            new Card("8 of diamonds", 80, R.drawable.diamonds_8),
            new Card("9 of diamonds", 90, R.drawable.diamonds_9),
            new Card("10 of diamonds", 100, R.drawable.diamonds_10),
            new Card("Jack of diamonds", 120, R.drawable.diamonds_jack),
            new Card("Queen of diamonds", 130, R.drawable.diamonds_queen),
            new Card("King of diamonds", 140, R.drawable.diamonds_king),
            new Card("Ace of diamonds", 110, R.drawable.diamonds_ace)
    };

    public Card(){
    }

    public Card(String cardName, int cardPrice, int cardImageResourceId){
        this.cardName = cardName;
        this.cardPrice = cardPrice;
        this.cardImageResourceId = cardImageResourceId;
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardPrice() {
        return cardPrice;
    }

    public int getCardImageResourceId() {
        return cardImageResourceId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardImageResourceId(int cardImageResourceId) {
        this.cardImageResourceId = cardImageResourceId;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }
}
