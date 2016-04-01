package com.example.tranv.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Trang2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);
    }
}
