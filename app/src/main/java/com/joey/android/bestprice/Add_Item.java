package com.joey.android.bestprice;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Item extends AppCompatActivity {
    SQLiteOpenHelper dB_helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);
        dB_helper = new DatabaseHelper(this);

        final EditText itemName = (EditText) findViewById(R.id.editText_itemName);
        final EditText price1 = (EditText) findViewById(R.id.editText_price1);
        final EditText market1 = (EditText) findViewById(R.id.editText_market1);
        final EditText price2 = (EditText) findViewById(R.id.editText_price2);
        final EditText market2 = (EditText) findViewById(R.id.editText_market2);

        Button submitItem = (Button) findViewById(R.id.submitItem_btn);

        submitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dB_helper.getWritableDatabase();

                String item_name = itemName.getText().toString();
                String price_1 = price1.getText().toString();
                String market_1 = market1.getText().toString();
                String price_2 = price2.getText().toString();
                String market_2 = market2.getText().toString();

                boolean valid = true;

                if(item_name.isEmpty() || item_name.length()< 2){
                    itemName.setError("At least 2 Character");
                    valid = false;
                }
                else{
                    itemName.setError(null);
                }

                if(price_1.isEmpty() || price_1.length() < 0 ){
                    price1.setError("Insert an amount");
                    valid = false;
                }
                else{
                    price1.setError(null);
                }

                if(market_1.isEmpty() || market_1.length() < 2){
                    market1.setError("At least 2 character");
                    valid = false;
                }
                else{
                    market1.setError(null);
                }

                if(price_2.isEmpty() || price_2.length() < 0 ){
                    price2.setError("Insert an amount");
                    valid = false;
                }
                else{
                    price2.setError(null);
                }

                if(market_2.isEmpty() || market_2.length() < 2){
                    market2.setError("At least 2 character");
                    valid = false;
                }
                else{
                    market2.setError(null);
                }

                if(valid == true) {
                    InsertData(item_name,price_1,market_1,price_2,market_2);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(Add_Item.this);
                    builder.setTitle("Information");
                    builder.setMessage("Item have been Inserted!");
                    builder.setPositiveButton("Back to homepage", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            finish();
                        }
                    });

                    builder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Add_Item.this, Add_Item.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Insert item failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void InsertData(String item_name, String price_1, String market_1, String price_2, String market_2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_ITEMNAME, item_name);
        contentValues.put(DatabaseHelper.COLUMN_PRICE1, price_1);
        contentValues.put(DatabaseHelper.COLUMN_MARKET1, market_1);
        contentValues.put(DatabaseHelper.COLUMN_PRICE2, price_2);
        contentValues.put(DatabaseHelper.COLUMN_MARKET2, market_2);
        long data = db.insert(DatabaseHelper.TABLE_NAME2, null, contentValues);
    }
}
