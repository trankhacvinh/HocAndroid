package note.ghichu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/**
 * Created by tranv on 4/8/2016.
 */

public class DBGhiChu {
    private static final String DATABASE_NAME = "DB_GHICHU";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TB_GHICHU";

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TIEUDE = "TieuDe";
    public static final String COLUMN_NOIDUNG = "NoiDung";
    public static final String COLUMN_ISXOA = "IsXoa";

    private static Context context;
    public static SQLiteDatabase db;
    private OpenHelper openHelper;

    public DBGhiChu(Context c) {
        DBGhiChu.context = c;
    }

    public DBGhiChu OpenConnection() {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    public void CloseConnection() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public boolean ThemGhiChu(GhiChu ghiChu) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIEUDE, ghiChu.getTieuDe());
            values.put(COLUMN_NOIDUNG, ghiChu.getNoiDung());
            values.put(COLUMN_ISXOA, ghiChu.getIsXoa());

            db.insert(TABLE_NAME, null, values);

            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean CapNhatGhiChu(int Id, GhiChu ghiChu) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIEUDE, ghiChu.getTieuDe());
            values.put(COLUMN_NOIDUNG, ghiChu.getNoiDung());
            values.put(COLUMN_ISXOA, ghiChu.getIsXoa());

            db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(Id)});
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Cursor TimKiemGhiChu(String input) {
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC";
            if (!TextUtils.isEmpty(input)) {
                query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TIEUDE + " like N'%" + input + "%' ORDER BY " + COLUMN_ID + " DESC";
            }
            Cursor cursor = db.rawQuery(query, null);

            return cursor;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean XoaGhiChu(int Id) {
        try {
            db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(Id)});
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TIEUDE + " NVARCHAR(500), " +
                    COLUMN_NOIDUNG + " TEXT, " +
                    COLUMN_ISXOA + " BIT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
