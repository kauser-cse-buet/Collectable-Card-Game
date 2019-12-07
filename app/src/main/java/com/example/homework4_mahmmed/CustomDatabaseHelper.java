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

    private static final String PLAYER_UNOPENED_PACKS_TABLE = "PLAYER_UNOPENED_PACKS";
    public static final String PLAYER_UNOPENED_PACKS_COLUMN_ID = "_id";
    public static final String PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID = "player_id";
    public static final String PLAYER_UNOPENED_PACKS_COLUMN_CARD_ID = "card_id";

    private static final String PLAYER_OPENED_PACKS_TABLE = "PLAYER_OPENED_PACKS";
    public static final String PLAYER_OPENED_PACKS_COLUMN_ID = "_id";
    public static final String PLAYER_OPENED_PACKS_COLUMN_PLAYER_ID = "player_id";
    public static final String PLAYER_OPENED_PACKS_COLUMN_CARD_ID = "card_id";



    public CustomDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE IF NOT EXISTS "  + CARD_TABLE_NAME + " (" +
                CARD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CARD_COLUMN_NAME + " TEXT, " +
                CARD_COLUMN_PRICE + " INTEGER, " +
                CARD_COLUMN_IMAGE_RESOURCE_ID + " INTEGER);";

        // create player
        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS "  + PLAYER_TABLE_NAME + " (" +
                PLAYER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_COLUMN_NAME + " TEXT, " +
                PLAYER_COLUMN_MONEY + " INTEGER);";

        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS "  + PLAYER_UNOPENED_PACKS_TABLE + " (" +
                PLAYER_UNOPENED_PACKS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID + " INTEGER, " +
                PLAYER_UNOPENED_PACKS_COLUMN_CARD_ID + " INTEGER," +
                " FOREIGN KEY(" + PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID + ") REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ")," +
                " FOREIGN KEY(" + PLAYER_UNOPENED_PACKS_COLUMN_CARD_ID + ") REFERENCES " + CARD_TABLE_NAME + "(" + CARD_COLUMN_ID + ")" +
                ");";

        db.execSQL(query);

        query = "CREATE TABLE IF NOT EXISTS "  + PLAYER_OPENED_PACKS_TABLE + " (" +
                PLAYER_OPENED_PACKS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PLAYER_OPENED_PACKS_COLUMN_PLAYER_ID + " INTEGER, " +
                PLAYER_OPENED_PACKS_COLUMN_CARD_ID + " INTEGER," +
                " FOREIGN KEY(" + PLAYER_OPENED_PACKS_COLUMN_PLAYER_ID + ") REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ")," +
                " FOREIGN KEY(" + PLAYER_OPENED_PACKS_COLUMN_CARD_ID + ") REFERENCES " + CARD_TABLE_NAME + "(" + CARD_COLUMN_ID + ")" +
                ");";

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

//    public getPlayerUnopenedPacks(SQLiteDatabase db, Player player){
//
//
//    }

    public int updatePlayer(SQLiteDatabase db, Player player){
        ContentValues playerValues = new ContentValues();
        playerValues.put(PLAYER_COLUMN_NAME, player.getName());
        playerValues.put(PLAYER_COLUMN_MONEY, player.getMoney());

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{
            player.get_id() + ""
        };

        int numberOfRowsAffected = db.update(PLAYER_TABLE_NAME, playerValues, whereClause, whereArgs);

        return numberOfRowsAffected;
    }

    public long insertIntoPlayerUnopenedCard(SQLiteDatabase db, Player player, Card card) {
        ContentValues playerUnopenedPacksValues = new ContentValues();
        playerUnopenedPacksValues.put(PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID, player.get_id());
        playerUnopenedPacksValues.put(PLAYER_UNOPENED_PACKS_COLUMN_CARD_ID, card.getId());

        return db.insert(PLAYER_UNOPENED_PACKS_TABLE, null, playerUnopenedPacksValues);
    }

    public void deleteFromPlayerUnopenedCard(SQLiteDatabase db, Player player, Card card) {
        String query = "DELETE FROM " + PLAYER_UNOPENED_PACKS_TABLE +
                        "WHERE " + PLAYER_UNOPENED_PACKS_COLUMN_ID + " in (select " + PLAYER_UNOPENED_PACKS_COLUMN_ID +
                        "   FROM " + PLAYER_UNOPENED_PACKS_TABLE +
                        "   WHERE " + PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID + " = " + Integer.toString(player.get_id()) + " and " + PLAYER_UNOPENED_PACKS_COLUMN_CARD_ID + " = " + Integer.toString(card.getId()) +
                        "   ORDER BY " + PLAYER_UNOPENED_PACKS_COLUMN_PLAYER_ID +
                        "   LIMIT 1);";

        db.execSQL(query);
    }

    public long insertIntoPlayerOpenedCard(SQLiteDatabase db, Player player, Card card) {
        ContentValues playerOpenedPacksValues = new ContentValues();
        playerOpenedPacksValues.put(PLAYER_OPENED_PACKS_COLUMN_PLAYER_ID, player.get_id());
        playerOpenedPacksValues.put(PLAYER_OPENED_PACKS_COLUMN_CARD_ID, card.getId());

        return db.insert(PLAYER_UNOPENED_PACKS_TABLE, null, playerOpenedPacksValues);
    }

    public boolean buyPacks(SQLiteDatabase db, Player player, Card card){
        int playerMoney = player.getMoney();
        int cardPrice = card.getPrice();

        if (playerMoney < cardPrice){
            return false;
        }
        else{
            updatePlayer(db, player);
            insertIntoPlayerUnopenedCard(db, player, card);

            int availableMoney = playerMoney - cardPrice;
            player.setMoney(availableMoney);
            player.unopenedCards.add(card);
            return true;
        }
    }

    public boolean openPacks(SQLiteDatabase db, Player player, Card card){
        if(player.unopenedCards.contains(card)){
            player.unopenedCards.remove(card);
            deleteFromPlayerUnopenedCard(db, player, card);

            player.openedCards.add(card);
            insertIntoPlayerOpenedCard(db, player, card);
            return true;
        }
        else{
            return false;
        }
    }
}
