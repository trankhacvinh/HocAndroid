package com.example.tranv.multiactivities.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDatabase {

    private static final String DATABASE_NAME = "DB_GHICHU";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_GHICHU = "TB_GHICHU";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TIEUDE = "TieuDe";
    public static final String COLUMN_NOIDUNG = "NoiDung";

    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    public NoteDatabase(Context c) {
        NoteDatabase.context = c;
    }

    public NoteDatabase Open() throws SQLException {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void Close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public boolean ThemGhiChu(NoteModel note) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIEUDE, note.getTieuDe());
            values.put(COLUMN_NOIDUNG, note.getNoiDung());

            db.insert(TABLE_GHICHU, null, values);

            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean CapNhatGhiChu(int Id, NoteModel note) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIEUDE, note.getTieuDe());
            values.put(COLUMN_NOIDUNG, note.getNoiDung());

            db.update(TABLE_GHICHU, values, COLUMN_ID + " = ?", new String[]{String.valueOf(Id)});
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Cursor TimKiemGhiChu(String input) {
        try {
            String query = "SELECT * FROM " + TABLE_GHICHU;
            if (!TextUtils.isEmpty(input)) {
                query = "SELECT * FROM " + TABLE_GHICHU + " WHERE " + COLUMN_TIEUDE + " like N'%" + input + "%'";
            }
            Cursor cursor = db.rawQuery(query, null);

            return cursor;
        } catch (Exception ex) {
            return null;
        }
    }
    /*public ArrayList<NoteModel> TimKiemGhiChu(String input) {
        try {
            ArrayList<NoteModel> noteList = new ArrayList<>();
            String query = "SELECT * FROM " + TABLE_GHICHU;
            if (!TextUtils.isEmpty(input)) {
                query = "SELECT * FROM " + TABLE_GHICHU + " WHERE " + COLUMN_TIEUDE + " like N'%" + input + "%'";
            }
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    NoteModel note = new NoteModel();
                    note.setId(Integer.parseInt(cursor.getString(0)));
                    note.setTieuDe(cursor.getString(1));
                    note.setNoiDung(cursor.getString(2));

                    noteList.add(note);
                } while (cursor.moveToNext());
            }
            return noteList;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }*/

    public boolean XoaGhiChu(int Id) {
        try {
            db.delete(TABLE_GHICHU, COLUMN_ID + " = ?", new String[]{String.valueOf(Id)});
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL("CREATE TABLE " + TABLE_GHICHU + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_TIEUDE + " TEXT, "
                    + COLUMN_NOIDUNG + " TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_GHICHU);
            onCreate(arg0);
        }
    }
}
