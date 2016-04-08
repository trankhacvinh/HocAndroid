package com.example.tranv.multiactivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tranv.multiactivities.Note.NoteModel;

import java.util.List;

public class CustomListViewNoteAdapter extends BaseAdapter {
    private List<NoteModel> noteModelArrayList;
    private LayoutInflater layoutInflater;
    private Context c;

    public CustomListViewNoteAdapter(Context context, List<NoteModel> arrayList) {
        c = context;
        noteModelArrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return noteModelArrayList.size();
    }

    @Override
    public NoteModel getItem(int position) {
        return noteModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_listview_note, null);
        }

        TextView tieuDe = (TextView) convertView.findViewById(R.id.textViewTieuDeGhiChu);
        TextView noiDung = (TextView) convertView.findViewById(R.id.textViewNoiDungGhiChu);
        NoteModel note = getItem(position);
        tieuDe.setText(note.getTieuDe());
        noiDung.setText(note.getNoiDung());

        return convertView;
    }

}
