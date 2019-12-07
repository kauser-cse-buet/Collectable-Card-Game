package com.example.homework4_mahmmed;


import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerStatsFragment extends Fragment {


    private final Player player;

    public PlayerStatsFragment(Player player) {
        // Required empty public constructor
        this.player = player;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_player_stats, container, false);

        TextView playerNameTextView = (TextView) view.findViewById(R.id.text_player_name);
        TextView playerMoneyTextView = (TextView) view.findViewById(R.id.text_player_money);
        TextView playerUnopenedTextView = (TextView) view.findViewById(R.id.text_player_unopened_packs);

        playerNameTextView.setText(player.getName());
        playerMoneyTextView.setText("Current Gold: " + player.getMoney());
        playerUnopenedTextView.setText("Number of Packs: " + player.unopenedCards.size());

        return view;
    }

}
