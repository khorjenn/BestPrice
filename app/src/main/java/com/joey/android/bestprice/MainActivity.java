package com.joey.android.bestprice;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper dB_helper;
    SQLiteDatabase db;
    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        final EditText txtemail = (EditText)findViewById(R.id.editText_Email);
        final EditText txtpassword = (EditText)findViewById(R.id.editText_Password);
        Button login_btn = (Button)findViewById(R.id.button_SignIn);
        TextView register_btn = (TextView) findViewById(R.id.button_Register);

        dB_helper = new DatabaseHelper(this);
        db = dB_helper.getReadableDatabase();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String admin = "adminonly5959@admin.com";

                boolean valid = true;

                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    txtemail.setError("Enter a valid email address");
                    valid = false;
                }
                else{
                    txtemail.setError(null);
                }

                if(password.isEmpty() || password.length() < 3 || password.length() > 10){
                    txtpassword.setError("Password length between 3 to 10 alphanumeric character");
                    valid = false;
                }
                else{
                    txtpassword.setError(null);
                }

                if(valid == true) {
                    mCursor= db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COLUMN_EMAIL +
                            "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?",new String[]{email,password});

                    if(mCursor != null){
                        if (mCursor.getCount() > 0){
                            mCursor.moveToFirst();

                            String txtName = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_FULLNAME));
                            String txtEmail = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));

                            if(email.equals(admin)){
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                                intent.putExtra("fullname", txtName);
                                intent.putExtra("email", txtEmail);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                    else{
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Alert");
                        builder.setMessage("Username or Password is wrong");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();}
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(),"Login Failed!",Toast.LENGTH_LONG).show();
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
