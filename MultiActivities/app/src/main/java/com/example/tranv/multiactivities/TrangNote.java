package com.example.tranv.multiactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tranv.multiactivities.Database.Note;
import com.example.tranv.multiactivities.Database.YourDatabase;

import java.util.ArrayList;
import java.util.List;

public class TrangNote extends AppCompatActivity {

    private ListView listView = (ListView) findViewById(R.id.listView);
    private final List<Note> noteList = new ArrayList<Note>();
    private ArrayAdapter<Note> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_note);

//        listView = (ListView) findViewById(R.id.listView);
//
//        YourDatabase db = new YourDatabase(this);
//        db.createDefaultNotes();
//
//        List<Note> list=  db.getAllNotes();
//        this.noteList.addAll(list);
//
//        this.listViewAdapter = new ArrayAdapter<Note>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList);
//
//        this.listView.setAdapter(this.listViewAdapter);
//
//        registerForContextMenu(this.listView);
    }
}
