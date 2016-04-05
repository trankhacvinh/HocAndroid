package com.example.tranv.multiactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.tranv.multiactivities.Database.MyDatabase;

public class MainActivity extends AppCompatActivity {

    private MyDatabase database = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);

        final SharedData sharedData = new SharedData();
        sharedData.sharedPreferences = getSharedPreferences("SharedMessage", MODE_PRIVATE);

        if (button1 != null) {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Trang1.class);
                    String message = "Đây là Trang 1";
                    intent.putExtra("message", message);
                    sharedData.saveData(sharedData.sharedPreferences, message);

                    boolean kq = InsertNote("Dữ liệu của note");
                    intent.putExtra("insertResult", kq);

                    MainActivity.this.startActivity(intent);
                }
            });
        }
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Trang2.class);
                    String message = "Đây là Trang 2";
                    intent.putExtra("message", message);
                    MainActivity.this.startActivity(intent);
                }
            });
        }
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Trang3.class);
                    String message = "Đây là Trang 3";
                    intent.putExtra("message", message);
                    sharedData.saveData(sharedData.sharedPreferences, message);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        if (button4 != null) {
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View trangmain) {
                    Intent intent = new Intent(MainActivity.this, Trang4.class);
                    String message = "Đây là Trang 4";
                    intent.putExtra("message", message);
                    sharedData.saveData(sharedData.sharedPreferences, message);
                    MainActivity.this.startActivity(intent);
                }
            });
        }

        if (button5 != null) {
            button5.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, TrangNote.class);

                    MainActivity.this.startActivity(intent);
                }
            });
        }
    }

    public boolean InsertNote(String data) {
        try {
            database.open();
            database.createData(data);
            database.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
