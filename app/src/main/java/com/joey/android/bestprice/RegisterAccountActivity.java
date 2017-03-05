package com.joey.android.bestprice;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAccountActivity extends AppCompatActivity {
    SQLiteOpenHelper dB_helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        dB_helper = new DatabaseHelper(this);

        Button button_register = (Button)findViewById(R.id.button_reg);
        TextView button_login = (TextView)findViewById(R.id.button_Login);

        final EditText txt_fullname = (EditText)findViewById(R.id.editName_reg);
        final EditText txt_email = (EditText)findViewById(R.id.editEmail_reg);
        final EditText txt_pass = (EditText)findViewById(R.id.editPass_reg);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dB_helper.getWritableDatabase();

                String fullname = txt_fullname.getText().toString();
                String email = txt_email.getText().toString();
                String pass = txt_pass.getText().toString();

                boolean valid = true;

                if(fullname.isEmpty() || fullname.length()< 3){
                    txt_fullname.setError("At least 3 Character");
                    valid = false;
                }
                else{
                    txt_fullname.setError(null);
                }

                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txt_email.setError("Enter a valid email address");
                    valid = false;
                }
                else{
                    txt_email.setError(null);
                }

                if(pass.isEmpty() || pass.length() < 3 || pass.length() > 10){
                    txt_pass.setError("Password length between 3 to 10 alphanumeric character");
                    valid = false;
                }
                else{
                    txt_pass.setError(null);
                }

                if(valid == true) {
                    InsertData(fullname, email, pass);

                    final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAccountActivity.this);

                    builder.setTitle("Information");
                    builder.setMessage("Your Account Have Been Created!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Sign up failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void InsertData(String fullName, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_FULLNAME, fullName);
        contentValues.put(DatabaseHelper.COLUMN_EMAIL, email);
        contentValues.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long data = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
