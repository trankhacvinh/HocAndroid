package com.example.tranv.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Trang1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang1);

        Intent intent = getIntent();

        String message = intent.getStringExtra("message");

        TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);

        final SharedData sharedData = new SharedData();
        sharedData.sharedPreferences = getSharedPreferences("SharedMessage", MODE_PRIVATE);
        String sharedMessage = sharedData.loadData(sharedData.sharedPreferences);
        Toast.makeText(getApplicationContext(), sharedMessage,Toast.LENGTH_SHORT).show();
        sharedData.clearData(sharedData.sharedPreferences);
    }
}
