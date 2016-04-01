package com.example.tranv.phu0104;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textview1 = (TextView) findViewById(R.id.textview1);
        textview1.setText("this is text11");

        Intent intent = getIntent();
        textview1.setText(intent.getStringExtra("dulieu2"));
    }

}
