package com.example.tranv.multiactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranv.multiactivities.Database.MyDatabase;

public class Trang1 extends AppCompatActivity {

    private MyDatabase database = new MyDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang1);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        boolean kq = intent.getBooleanExtra("insertResult", false);
        TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        textViewMessage.setText(message);

        String kqInsert = "Không thành công!";
        if (kq) {
            kqInsert = "Thành công!";

            TextView noteList = (TextView) findViewById(R.id.txtNoteList);
            database.open();
            String ds = database.getData();
            database.close();
            noteList.setText(ds);
        }
        Toast.makeText(getApplicationContext(), kqInsert, Toast.LENGTH_SHORT).show();
    }
}
