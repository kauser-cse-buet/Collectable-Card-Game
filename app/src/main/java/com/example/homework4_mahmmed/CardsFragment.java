package com.example.homework4_mahmmed;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
            try {
                db = helper.getWritableDatabase();
                Cursor c = helper.getCards(db);

                cardList = new ArrayList<>();

                if(c.getCount() > 0){
                    while(c.moveToNext()){
                        Card card = new Card();

                        card.setId(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_ID)));
                        card.setName(c.getString(c.getColumnIndex(helper.CARD_COLUMN_NAME)));
                        card.setPrice(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_PRICE)));
                        card.setImageResourceId(c.getInt(c.getColumnIndex(helper.CARD_COLUMN_IMAGE_RESOURCE_ID)));

                        cardList.add(card);
                    }
                }
                else{

                    for(Card baseCard: Card.cards){
                        long id = helper.insertCard(db, baseCard.getName(), baseCard.getPrice(), baseCard.getImageResourceId());
                        Card card = new Card(baseCard.getName(), baseCard.getPrice(), baseCard.getImageResourceId());
                        card.setId((int)id);
                        cardList.add(card);
                    }
                }

                db.close();
            }
            catch (SQLException e){
                Toast.makeText(getContext(), "Database unavilable.", Toast.LENGTH_SHORT).show();
                Log.d("Exception: DB N/A", e.getStackTrace().toString());
            }

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

//    public void addAllCards(){
//        try {
//
//        }
//        db = helper.getWritableDatabase();
//
//        List<Card> cards  = new ArrayList<>();
//        for(Card card: Card.cards){
//            long id = helper.insertCard(db, card.getName(), card.getPrice(), card.getImageResourceId());
//
//
//            cards.add(card);
//
//            card.setId((int)id);
//        }
//    }


}
