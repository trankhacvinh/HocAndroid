package com.example.tranv.multiactivities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tranv.multiactivities.Note.NoteModel;

import java.io.Serializable;
import java.util.ArrayList;


public class FragmentGhiChu extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_ghi_chu, container, false);
        //Bundle bundle = this.getArguments();
        //Serializable notes = bundle.getSerializable("notes");
//        ListView a = (ListView) v.findViewById(R.id.listViewGhiChuFragment);
//        ArrayAdapter<NoteModel> arrayAdapter
//                = new ArrayAdapter<NoteModel>(this, android.R.layout.simple_list_item_1, notes);
//        if (a != null) {
//            a.setAdapter(arrayAdapter);
//        }
        return v;
    }

}
