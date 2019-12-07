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


    public PlayerStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_player_stats, container, false);

        TextView playerNameTextView = (TextView) view.findViewById(R.id.text_player_name);
        TextView playerMoneyTextView = (TextView) view.findViewById(R.id.text_player_money);


        try {
            CustomDatabaseHelper helper = new CustomDatabaseHelper(view.getContext());
            SQLiteDatabase db = helper.getReadableDatabase();

            Player player = helper.getPlayerByName(db, Player.players[0].getName());

//            if (player != null){
            playerNameTextView.setText(player.getName());
            playerMoneyTextView.setText("Available Money: " + player.getMoney());

//            }
            db.close();


        }
        catch (SQLException e){
            Toast.makeText(view.getContext(), "Database not available", Toast.LENGTH_SHORT).show();
            Log.d("DB N/A", e.getStackTrace().toString());
        }

        return view;
    }

}
