package com.example.tranv.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Trang2 extends AppCompatActivity {

    Button btnLogin = (Button) findViewById(R.id.btnLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        if (textViewMessage != null) {
            textViewMessage.setText(message);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
