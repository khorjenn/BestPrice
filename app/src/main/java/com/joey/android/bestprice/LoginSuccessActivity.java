package com.joey.android.bestprice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        TextView txt_name = (TextView)findViewById(R.id.txt_success_name);
        TextView txt_email = (TextView)findViewById(R.id.txt_success_email);
        Button btn_logout = (Button)findViewById(R.id.button_logout);

        Intent intent = getIntent();

        String LoginName = intent.getStringExtra("fullname");
        String LoginEmail = intent.getStringExtra("email");
        txt_name.setText("Welcome, " + LoginName);
        txt_email.setText(LoginEmail);

        Button ListItemButton = (Button)findViewById(R.id.button_checkItem);
        ListItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginSuccessActivity.this, List_Item.class);
                startActivity(i);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccessActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you want to logout?");

                builder.setPositiveButton("Confirm!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(LoginSuccessActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("Not Now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
