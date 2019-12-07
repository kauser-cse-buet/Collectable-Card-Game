package com.example.homework4_mahmmed;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PlayerStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        TextView playerNameTextView = (TextView) findViewById(R.id.text_player_name);
        TextView playerMoneyTextView = (TextView) findViewById(R.id.text_player_money);


        try {
            CustomDatabaseHelper helper = new CustomDatabaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            Player player = helper.getPlayerByName(db, Player.players[0].getName());

//            if (player != null){
            playerNameTextView.setText(player.getName());
            playerMoneyTextView.setText("Available Money: " + player.getMoney());

//            }
            db.close();


        }
        catch (SQLException e){
            Toast.makeText(this, "Database not available", Toast.LENGTH_SHORT).show();
            Log.d("DB N/A", e.getStackTrace().toString());
        }
    }
}
