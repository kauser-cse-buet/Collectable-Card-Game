package com.example.homework4_mahmmed;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment {

    private static List<Card> cardList;
    private CustomDatabaseHelper helper;
    private SQLiteDatabase db;

    public CardsFragment() {
        // Required empty public constructor

    }

    public List<Card> getCardList() {
        helper = new CustomDatabaseHelper(getContext());

        if (cardList == null){
            db = helper.getWritableDatabase();
            Cursor c = helper.getCards(db);

            cardList = new ArrayList<>();

            if(c.getCount() > 0){
                while(c.moveToNext()){
                    Card card = new Card();

                    card.setCardId(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_ID)));
                    card.setCardName(c.getString(c.getColumnIndex(helper.CARD_COLUMN_NAME)));
                    card.setCardPrice(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_PRICE)));
                    card.setCardImageResourceId(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_IMAGE_RESOURCE_ID)));

                    cardList.add(card);
                }
            }
            else{

                for(Card baseCard: Card.cards){
                    long id = helper.insertCard(db, baseCard.getCardName(), baseCard.getCardPrice(), baseCard.getCardImageResourceId());
                    Card card = new Card(baseCard.getCardName(), baseCard.getCardPrice(), baseCard.getCardImageResourceId());
                    card.setCardId((int)id);
                    cardList.add(card);
                }
            }

            db.close();
        }

        return cardList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView cardRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_cards, container, false);

        CardInfoAdapter cardInfoAdapter = new CardInfoAdapter(getCardList());
        cardRecycler.setAdapter(cardInfoAdapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        cardRecycler.setLayoutManager(linearLayoutManager);

        StaggeredGridLayoutManager stagGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        cardRecycler.setLayoutManager(stagGridLayoutManager);

        return cardRecycler;
    }

    public void addAllCards(){
        db = helper.getWritableDatabase();

        List<Card> cards  = new ArrayList<>();
        for(Card card: Card.cards){
            long id = helper.insertCard(db, card.getCardName(), card.getCardPrice(), card.getCardImageResourceId());


            cards.add(card);

            card.setCardId((int)id);
        }
    }


}
