package com.example.homework4_mahmmed;


import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpenPacksFragment extends Fragment implements CardInfoAdapter.OnNoteListener {
    private final String TAG = "OpenPacksFragment";
    private final Player player;
    private CardInfoAdapter cardInfoAdapter;

    public OpenPacksFragment(Player player) {
        // Required empty public constructor
        this.player = player;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_packs, container, false);

        TextView textViewHeader = (TextView)view.findViewById(R.id.text_header);
        textViewHeader.setText(R.string.nav_open_packs);

        TextView textViewName = (TextView) view.findViewById(R.id.text_player_name);
        textViewName.setVisibility(View.GONE);

        TextView textViewUnopenedPacks = (TextView) view.findViewById(R.id.text_player_unopened_packs);
        textViewUnopenedPacks.setText("Number of Packs: " + player.unopenedCards.size());

        TextView textViewMoney = (TextView) view.findViewById(R.id.text_player_money);
        textViewMoney.setVisibility(View.GONE);

        initRecyclerView(view);


        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view_container);
        cardInfoAdapter = new CardInfoAdapter(player.unopenedCards, this);

        recyclerView.setAdapter(cardInfoAdapter);

        StaggeredGridLayoutManager stagGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(stagGridLayoutManager);
    }

    @Override
    public void onNoteClicked(final int position) {
        final Card card = this.player.unopenedCards.get(position);

        Log.d(TAG, card.getName());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Are you sure, You wanted to open this card: " + card.getName() + "?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Log.d(TAG, player.getName() + " buy " + card.getName());

                        try {
                            CustomDatabaseHelper helper = new CustomDatabaseHelper(getContext());
                            SQLiteDatabase db = helper.getWritableDatabase();

                            boolean success = helper.openPacks(db, player, card);

                            String logMessage = success + ", " +
                                    card.getName() + ", Total Unopened cards: " + player.unopenedCards.size() +
                                    " , Total opened cards: " + player.openedCards.size();

                            Log.d(TAG, logMessage);

                            if(success){
                                TextView textViewUnopenedPacks = (TextView) getActivity().findViewById(R.id.text_player_unopened_packs);
                                textViewUnopenedPacks.setText("Number of Packs: " + player.unopenedCards.size());
                                cardInfoAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Successfully opened " + card.getName() + " and added to your collection.", Toast.LENGTH_SHORT).show();
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



    }
}
