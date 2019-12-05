package com.example.homework4_mahmmed;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "card_collection_game";
    private static final int DB_VERSION = 1;

    private static String CARD_TABLE_NAME = "CARD";
    private static String CARD_COLUMN_ID = "_id";
    private static String CARD_COLUMN_NAME = "NAME";
    private static String CARD_COLUMN_PRICE= "PRICE";
    private static String CARD_COLUMN_IMAGE_RESOURCE_ID = "IMAGE_RESOURCE_ID";


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
        db.execSQL(query);

        insertDrink(db, "2 of Heart", 2, R.drawable.clubs_2);
        insertDrink(db, "3 of Heart", 3, R.drawable.clubs_3);
        insertDrink(db, "4 of Heart", 4, R.drawable.clubs_4);
    }

    private static void insertDrink(SQLiteDatabase db, String name, int price, int resourceId) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put(CARD_COLUMN_NAME, name);
        drinkValues.put(CARD_COLUMN_PRICE, price);
        drinkValues.put(CARD_COLUMN_IMAGE_RESOURCE_ID, resourceId);

        db.insert(CARD_TABLE_NAME, null, drinkValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
