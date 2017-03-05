package com.joey.android.bestprice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joey on 3/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "information.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user_profile";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_FULLNAME = "user_name";
    public static final String COLUMN_EMAIL = "user_email";
    public static final String COLUMN_PASSWORD = "user_password";

    public static final String TABLE_NAME2 = "items_table";
    public static final String COLUMN_ID2 = "ID";
    public static final String COLUMN_ITEMNAME = "ITEM_NAME";
    public static final String COLUMN_PRICE1 = "PRICE_1";
    public static final String COLUMN_MARKET1 = "MARKET_1";
    public static final String COLUMN_PRICE2 = "PRICE_2";
    public static final String COLUMN_MARKET2 = "MARKET_2";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FULLNAME + " TEXT, "+
            COLUMN_EMAIL + " TEXT, " +
            COLUMN_PASSWORD + " TEXT" +  ")";

    private static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_NAME2 + " ("
            + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ITEMNAME + " TEXT, " + COLUMN_PRICE1 + " INTEGER, "
            + COLUMN_MARKET1 + " TEXT, " + COLUMN_PRICE2 + " INTEGER, "
            + COLUMN_MARKET2 + " TEXT"+ ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME2);
        onCreate(db);
    }


}
