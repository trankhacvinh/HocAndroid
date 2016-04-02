package com.example.tranv.phu0104;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but1 = (Button) findViewById(R.id.button1);
        Button but2= (Button) findViewById(R.id.button2);
        Button but3= (Button) findViewById(R.id.button3);
        Button but4= (Button) findViewById(R.id.button4);

        if (but1 != null) {
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dulieuguidisangtrang1= new Intent(MainActivity.this, trang1.class);
    dulieuguidisangtrang1.putExtra("bucthu","day la du lieu cua buc thu");
                    MainActivity.this.startActivity(dulieuguidisangtrang1);

                }
            });
        }

        if (but2 != null) {
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent aa= new Intent(MainActivity.this, trang2.class);
                    MainActivity.this.startActivity(aa);
                }
            });
        }


    }
}
