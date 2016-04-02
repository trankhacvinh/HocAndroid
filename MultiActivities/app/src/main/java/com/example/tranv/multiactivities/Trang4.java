package com.example.tranv.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Trang4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang4);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.textViewTrang4);
        textView.setText(intent.getStringExtra("dulieu"));

        final SharedData sharedData = new SharedData();
        sharedData.sharedPreferences = getSharedPreferences("SharedMessage", MODE_PRIVATE);
        String sharedMessage = sharedData.loadData(sharedData.sharedPreferences);
        Toast.makeText(getApplicationContext(), sharedMessage, Toast.LENGTH_SHORT).show();
        sharedData.clearData(sharedData.sharedPreferences);
    }
}
