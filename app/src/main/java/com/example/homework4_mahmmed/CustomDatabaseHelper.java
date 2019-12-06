package com.example.homework4_mahmmed;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        playerValues.put(PLAYER_COLUMN_NAME, "Kauser");
        playerValues.put(PLAYER_COLUMN_MONEY, 1000);

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
}
