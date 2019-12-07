package com.example.homework4_mahmmed;


import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchasePacksFragment extends Fragment implements CardInfoAdapter.OnNoteListener{


    private final Player player;
    private final List<Card> cardList;
    private final String TAG = "PurchasePacksFragment";

    public PurchasePacksFragment(Player player, List<Card> cardList) {
        // Required empty public constructor
        this.player = player;
        this.cardList = cardList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_purchase_packs, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_purchase_packs);

        TextView textViewName = (TextView) view.findViewById(R.id.text_player_name);
        textViewName.setVisibility(View.GONE);

        TextView textViewUnopenedPacks = (TextView) view.findViewById(R.id.text_player_unopened_packs);
        textViewUnopenedPacks.setText("Number of Packs: " + player.unopenedCards.size());

        TextView textViewMoney = (TextView) view.findViewById(R.id.text_player_money);
        textViewMoney.setText("Current Gold: " + player.getMoney());


        Button purchaseButton = (Button) view.findViewById(R.id.button_puchase_packs);
        LinearLayout buttonRecyclerViewContainer = (LinearLayout)view.findViewById(R.id.button_recycler_view_container);


        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                initRecyclerView(view);
            }
        });

        List<Card> purchaseableCardList = getPurchaseableCardList(player, cardList);

        if(purchaseableCardList.size() < 0){
            buttonRecyclerViewContainer.setAlpha(0);
        }
        else{
            buttonRecyclerViewContainer.setAlpha(1);
        }

        return view;
    }

    private List<Card> getPurchaseableCardList(Player player, List<Card> cardList){
        List<Card> purchaseableCardList = new ArrayList<>();

        for(Card card: cardList){
            if (card.getPrice() <= player.getMoney()){
                purchaseableCardList.add(card);
            }
        }

        return purchaseableCardList;
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view_container);
        CardInfoAdapter cardInfoAdapter = new CardInfoAdapter(cardList, this);

        recyclerView.setAdapter(cardInfoAdapter);

        StaggeredGridLayoutManager stagGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(stagGridLayoutManager);
    }

    @Override
    public void onNoteClicked(final int position) {
        Log.d(TAG, cardList.get(position).getName());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Are you sure, You wanted to buy this card");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Log.d(TAG, player.getName() + " buy " + cardList.get(position).getName());

                                try {
                                    CustomDatabaseHelper helper = new CustomDatabaseHelper(getContext());
                                    SQLiteDatabase db = helper.getWritableDatabase();

                                    boolean success = helper.buyPacks(db, player, cardList.get(position));

                                    Log.d(TAG, success + ", " + player.getMoney() + ", " + player.unopenedCards.size());

                                    if(success){
                                        TextView textViewUnopenedPacks = (TextView) getActivity().findViewById(R.id.text_player_unopened_packs);
                                        textViewUnopenedPacks.setText("Number of Packs: " + player.unopenedCards.size());

                                        TextView textViewMoney = (TextView) getActivity().findViewById(R.id.text_player_money);
                                        textViewMoney.setText("Current Gold: " + player.getMoney());
                                    }

                                    LinearLayout buttonRecyclerViewContainer = (LinearLayout)getActivity().findViewById(R.id.button_recycler_view_container);

                                    List<Card> purchaseableCardList = getPurchaseableCardList(player, cardList);

                                    if(purchaseableCardList.size() < 0) {
                                        buttonRecyclerViewContainer.setAlpha(0);
                                    }

                                }
                                catch (SQLiteException e){
                                    Log.d(TAG, e.getStackTrace().toString());
                                }
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

//        Toast.makeText(getContext(), cardList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
