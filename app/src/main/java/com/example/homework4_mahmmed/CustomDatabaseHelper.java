package com.example.homework4_mahmmed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "card_collection_game";
    private static final int DB_VERSION = 1;

    public static final String CARD_TABLE_NAME = "CARD";
    public static final String CARD_COLUMN_ID = "_id";
    public static final String CARD_COLUMN_NAME = "NAME";
    public static final String CARD_COLUMN_PRICE= "PRICE";
    public static final String CARD_COLUMN_IMAGE_RESOURCE_ID = "IMAGE_RESOURCE_ID";

    public static final String PLAYER_TABLE_NAME = "PLAYER";
    public static final String PLAYER_COLUMN_ID = "_id";
    public static final String PLAYER_COLUMN_NAME = "NAME";
    public static final String PLAYER_COLUMN_MONEY= "MONEY";


    public CustomDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE "  + CARD_TABLE_NAME + " (" +
                CARD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CARD_COLUMN_NAME + " TEXT, " +
                CARD_COLUMN_PRICE + " INTEGER, " +
                CARD_COLUMN_IMAGE_RESOURCE_ID + " INTEGER);";

        // create player
        db.execSQL(query);

        query = "CREATE TABLE "  + PLAYER_TABLE_NAME + " (" +
                PLAYER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_COLUMN_NAME + " TEXT, " +
                PLAYER_COLUMN_MONEY + " INTEGER);";

        db.execSQL(query);

        ContentValues playerValues = new ContentValues();
        playerValues.put(PLAYER_COLUMN_NAME, Player.players[0].getName());
        playerValues.put(PLAYER_COLUMN_MONEY, Player.players[0].getMoney());

        db.insert(PLAYER_TABLE_NAME, null, playerValues);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertCard(SQLiteDatabase db, String name, int price, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put(CARD_COLUMN_NAME, name);
        drinkValues.put(CARD_COLUMN_PRICE, price);
        drinkValues.put(CARD_COLUMN_IMAGE_RESOURCE_ID, resourceId);

        return db.insert(CARD_TABLE_NAME, null, drinkValues);
    }

    public Cursor getCards(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + CARD_TABLE_NAME, null);
        return c;
    }

    public long insertPlayer(SQLiteDatabase db, String name, int money) {
        ContentValues playerValues = new ContentValues();
        playerValues.put(PLAYER_COLUMN_NAME, name);
        playerValues.put(PLAYER_COLUMN_MONEY, money);

        return db.insert(PLAYER_TABLE_NAME, null, playerValues);
    }

    public Cursor getPlayers(SQLiteDatabase db){
        Cursor c = db.rawQuery("SELECT * FROM " + PLAYER_COLUMN_NAME, null);
        return c;
    }

    public Player getPlayerByName(SQLiteDatabase db, String name) {
//        ContentValues playerValues = new ContentValues();
//        playerValues.put(PLAYER_COLUMN_NAME, name);
//        playerValues.put(PLAYER_COLUMN_MONEY, money);
//
//        return db.insert(PLAYER_TABLE_NAME, null, playerValues);
        Player p = null;

        Cursor cursor = db.query(PLAYER_TABLE_NAME,
                new String[]{PLAYER_COLUMN_ID, PLAYER_COLUMN_NAME, PLAYER_COLUMN_MONEY},
                PLAYER_COLUMN_NAME + " = ?",
                new String[]{name},
                null,
                null,
                null
                );

        List<Player> players = new ArrayList<>();

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Player player = new Player();

                int p_id = cursor.getInt(cursor.getColumnIndex(this.PLAYER_COLUMN_ID));
                String p_name = cursor.getString(cursor.getColumnIndex(this.PLAYER_COLUMN_NAME));
                int p_money = cursor.getInt(cursor.getColumnIndex(this.PLAYER_COLUMN_MONEY));

                player.set_id(p_id);
                player.setName(p_name);
                player.setMoney(p_money);

                players.add(player);
            }
        }

        if(players.size() > 0){
            p = players.get(0);
        }

        return p;
    }
}
