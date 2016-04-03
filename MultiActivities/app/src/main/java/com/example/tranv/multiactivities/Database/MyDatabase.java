package com.example.tranv.multiactivities.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase {
    /*Tên database*/
    private static final String DATABASE_NAME = "DB_HOCANDROID";

    /*Version database*/
    private static final int DATABASE_VERSION = 1;

    /*Tên tabel và các column trong database*/
    private static final String TABLE_NOTE = "TB_NOTE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "note";

    /*Các đối tượng khác*/
    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    /*Hàm dựng, khởi tạo đối tượng*/
    public MyDatabase(Context c) {
        MyDatabase.context = c;
    }

    /*Hàm mở kết nối tới database*/
    public MyDatabase open() throws SQLException {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    /*Hàm đóng kết nối với database*/
    public void close() {
        openHelper.close();
    }

    /*Hàm createData dùng để chèn dữ mới dữ liệu vào database*/
    public long createData(String noteValue) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTE, noteValue);
        return db.insert(TABLE_NOTE, null, cv);
    }

    /*Hàm getData trả về toàn bộ dữ liệu của table ACCOUNT của database dưới 1 chuỗi*/
    public String getData() {
        String[] columns = new String[]{COLUMN_ID, COLUMN_NOTE};
        Cursor c = db.query(TABLE_NOTE, columns, null, null, null, null, null);
        /*if(c==null)
            Log.v("Cursor", "C is NULL");*/
        String result = "";
        //getColumnIndex(COLUMN_ID); là lấy chỉ số, vị trí của cột COLUMN_ID ...
        int iRow = c.getColumnIndex(COLUMN_ID);
        int iN = c.getColumnIndex(COLUMN_NOTE);
        //Vòng lặp lấy dữ liệu của con trỏ
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + " " + c.getString(iRow)
                    + " - id:" + c.getString(iRow)
                    + " - note:" + c.getString(iN) + "\n";
        }
        c.close();
        //Log.v("Result", result);
        return result;
    }

    //---------------- class OpenHelper ------------------
    private static class OpenHelper extends SQLiteOpenHelper {

        /*Hàm dựng khởi tạo 1 OpenHelper*/
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*Tạo mới database*/
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL("CREATE TABLE " + TABLE_NOTE + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOTE + " TEXT NOT NULL);");
        }

        /*Kiểm tra phiên bản database nếu khác sẽ thay đổi*/
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
            onCreate(arg0);
        }
    }
}
