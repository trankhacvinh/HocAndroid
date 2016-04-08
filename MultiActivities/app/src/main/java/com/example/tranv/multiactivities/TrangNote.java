package com.example.tranv.multiactivities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranv.multiactivities.Note.NoteDatabase;
import com.example.tranv.multiactivities.Note.NoteModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TrangNote extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_note);

        try {
            LoadNoteList();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Button btnThemMoiNote = (Button) findViewById(R.id.btnThemMoiNote);
        if (btnThemMoiNote != null) {
            btnThemMoiNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        AddNote();
                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        final ListView listView = (ListView) findViewById(R.id.listViewGhiChu);
        if (listView != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    NoteModel note = (NoteModel) o;
                    EditText txtTieuDe = (EditText) findViewById(R.id.txtTieuDe);
                    EditText txtNoiDung = (EditText) findViewById(R.id.txtNoiDung);
                    if (txtTieuDe != null) {
                        txtTieuDe.setText(note.getTieuDe());
                    }
                    if (txtNoiDung != null) {
                        txtNoiDung.setText(note.getNoiDung());
                    }
                    Toast t = Toast.makeText(getApplicationContext(), "Id :" + " " + note.getId(), Toast.LENGTH_SHORT);
                    t.show();
                }
            });
        }
    }

    private void LoadNoteList() throws SQLException {
        TextView textViewDanhSachGhiChu = (TextView) findViewById(R.id.textViewDanhSachGhiChu);
        NoteDatabase database = new NoteDatabase(this);

        database.Open();
        Cursor list = database.TimKiemGhiChu("");

        String result = "";
        List<NoteModel> notes = new ArrayList<>();

        //NoteModel[] noteModels = new NoteModel[list.getCount()];
        if (list.moveToFirst()) {
            do {
                int Id = Integer.parseInt(list.getString(0));
                String tieuDe = list.getString(1);
                String noiDung = list.getString(2);
                result += "Id : " + Id +
                        " Tiêu đề : " + tieuDe +
                        " Nội dung : " + noiDung + "\n";

                NoteModel note = new NoteModel(Id,tieuDe,noiDung);
                notes.add(note);
                //noteModels[list.getPosition()] = note;
            } while (list.moveToNext());
        }

        if (textViewDanhSachGhiChu != null) {
            textViewDanhSachGhiChu.setText(result);
        }

       /* String[] listString = new String[noteModels.length];
        for (int i=0;i<noteModels.length;i++){
            listString[i] = noteModels[i].getNoiDung();
        }*/

        ListView listView = (ListView) findViewById(R.id.listViewGhiChu);
        CustomListViewNoteAdapter customListViewNoteAdapter = new CustomListViewNoteAdapter(this, notes);

        listView.setAdapter(customListViewNoteAdapter);

//        ArrayAdapter<String> arrayAdapter
//                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listString);
//        if (listView != null) {
//            listView.setAdapter(arrayAdapter);
//        }
        //LoadFragmentListNoteItem(notes);
        database.Close();
    }

    public void AddNote() throws SQLException {
        EditText txtTieuDe = (EditText) findViewById(R.id.txtTieuDe);
        EditText txtNoiDung = (EditText) findViewById(R.id.txtNoiDung);
        NoteDatabase database = new NoteDatabase(this);

        String tieuDe = (txtTieuDe != null) ? txtTieuDe.getText().toString() : "";
        assert txtNoiDung != null;
        String noiDung = txtNoiDung.getText().toString();

        NoteModel note = new NoteModel();
        note.setTieuDe(tieuDe);
        note.setNoiDung(noiDung);
        database.Open();
        boolean kq = database.ThemGhiChu(note);
        if (kq) {
            Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
            LoadNoteList();
        } else {
            Toast.makeText(getApplicationContext(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    public void LoadFragmentListNoteItem(ArrayList<NoteModel> notes){
        Fragment frag = new FragmentGhiChu();
        Bundle bundle = new Bundle();
        bundle.putSerializable("notes", notes);
        frag.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.flDanhSachGhiChu, frag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    private void LoadNoteList(){
//        ListView listView = (ListView) findViewById(R.id.listView);
//        final List<Note> noteList = new ArrayList<Note>();
//        ArrayAdapter<Note> listViewAdapter;
//
//        listView = (ListView) findViewById(R.id.listView);
//
//        YourDatabase db = new YourDatabase(this);
//        db.createDefaultNotes();
//
//        List<Note> list=  db.getAllNotes();
//        noteList.addAll(list);
//
//        listViewAdapter = new ArrayAdapter<Note>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, noteList);
//
//        listView.setAdapter(listViewAdapter);
//
//        registerForContextMenu(listView);
//    }
}
