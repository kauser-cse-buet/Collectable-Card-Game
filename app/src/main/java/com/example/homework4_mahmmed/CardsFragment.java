package com.example.homework4_mahmmed;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment  implements CardInfoAdapter.OnNoteListener {

    private final List<Card> cardList;
    private CustomDatabaseHelper helper;
    private SQLiteDatabase db;

    public CardsFragment(List<Card> cardsList) {
        this.cardList = cardsList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView cardRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_cards, container, false);

        CardInfoAdapter cardInfoAdapter = new CardInfoAdapter(cardList, this);
        cardRecycler.setAdapter(cardInfoAdapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        cardRecycler.setLayoutManager(linearLayoutManager);

        StaggeredGridLayoutManager stagGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        cardRecycler.setLayoutManager(stagGridLayoutManager);

        return cardRecycler;
    }


    @Override
    public void onNoteClicked(int position) {

    }
}
