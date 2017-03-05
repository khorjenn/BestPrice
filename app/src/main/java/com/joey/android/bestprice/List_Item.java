package com.joey.android.bestprice;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Item extends AppCompatActivity {
    SQLiteOpenHelper dB_helper;
    SQLiteDatabase db;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> PRICE1_ArrayList = new ArrayList<String>();
    ArrayList<String> MARKET1_ArrayList = new ArrayList<String>();
    ArrayList<String> PRICE2_ArrayList = new ArrayList<String>();
    ArrayList<String> MARKET2_ArrayList = new ArrayList<String>();

    ListView sListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__item);

        sListView = (ListView) findViewById(R.id.sListView);

        dB_helper = new DatabaseHelper(this);

        getData();

    }

    public void getData() {
        db = dB_helper.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME2, null);

        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        PRICE1_ArrayList.clear();
        MARKET1_ArrayList.clear();
        PRICE2_ArrayList.clear();
        MARKET2_ArrayList.clear();

        if (data.moveToFirst()) {
            do {
                ID_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_ID2)));
                NAME_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_ITEMNAME)));
                PRICE1_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_PRICE1)));
                MARKET1_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_MARKET1)));
                PRICE2_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_PRICE2)));
                MARKET2_ArrayList.add(data.getString(data.getColumnIndex(DatabaseHelper.COLUMN_MARKET2)));
            }
            while (data.moveToNext());
        }
        SQLiteListAdapater ListAdapter = new SQLiteListAdapater(List_Item.this, ID_ArrayList, NAME_ArrayList,
                PRICE1_ArrayList, MARKET1_ArrayList, PRICE2_ArrayList, MARKET2_ArrayList);
        sListView.setAdapter(ListAdapter);
        data.close();
    }
}
