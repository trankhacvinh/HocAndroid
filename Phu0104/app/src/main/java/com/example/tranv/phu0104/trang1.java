package com.example.tranv.phu0104;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class trang1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang1);

        Intent dulieunhanduoctutrangmain = getIntent();
        String noidungbucthu = dulieunhanduoctutrangmain.getStringExtra("bucthu");
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        if (textView2 != null) {
            textView2.setText(noidungbucthu);
        }
    }
}
