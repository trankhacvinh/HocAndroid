package note.ghichu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

public class DBGhiChu {
    private static final String DATABASE_NAME = "DB_GHICHU";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_GHICHU = "TB_GHICHU";
    public static final String COLUMN_GHICHU_ID = "Id";
    public static final String COLUMN_GHICHU_TIEUDE = "TieuDe";
    public static final String COLUMN_GHICHU_NOIDUNG = "NoiDung";
    public static final String COLUMN_GHICHU_NGAYTAO = "NgayTao";
    public static final String COLUMN_GHICHU_NGAYSUA = "NgaySua";
    public static final String COLUMN_GHICHU_ISXOA = "IsXoa";
    //public static final String COLUMN_GHICHU_IMAGE = "Image";

    private static final String TABLE_NAME_GHICHU_HINH = "TB_GHICHU_HINH";
    public static final String COLUMN_GHICHU_HINH_ID = "Id";
    public static final String COLUMN_GHICHU_HINH_IDGHICHU = "IdGhiChu";
    public static final String COLUMN_GHICHU_HINH_NGAYTAO = "NgayTao";
    public static final String COLUMN_GHICHU_HINH_ISXOA = "IsXoa";
    public static final String COLUMN_GHICHU_HINH_IMAGE = "Image";

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
            values.put(COLUMN_GHICHU_TIEUDE, ghiChu.getTieuDe());
            values.put(COLUMN_GHICHU_NOIDUNG, ghiChu.getNoiDung());
            values.put(COLUMN_GHICHU_ISXOA, ghiChu.getIsXoa());
//            if(ghiChu.getImageByte().length > 0){
//                values.put(COLUMN_GHICHU_IMAGE, ghiChu.getImageByte());
//            }
            values.put(COLUMN_GHICHU_NGAYTAO, ghiChu.getNgayTao());
            values.put(COLUMN_GHICHU_NGAYSUA, ghiChu.getNgayTao());

            db.insert(TABLE_NAME_GHICHU, null, values);

            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean CapNhatGhiChu(int Id, GhiChu ghiChu) {
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_GHICHU_TIEUDE, ghiChu.getTieuDe());
            values.put(COLUMN_GHICHU_NOIDUNG, ghiChu.getNoiDung());
            values.put(COLUMN_GHICHU_ISXOA, ghiChu.getIsXoa());
//            if(ghiChu.getImageByte().length > 0){
//                values.put(COLUMN_GHICHU_IMAGE, ghiChu.getImageByte());
//            }
            values.put(COLUMN_GHICHU_NGAYTAO, ghiChu.getNgayTao());
            values.put(COLUMN_GHICHU_NGAYSUA, ghiChu.getNgayTao());

            db.update(TABLE_NAME_GHICHU, values, COLUMN_GHICHU_ID + " = ?", new String[]{String.valueOf(Id)});
            db.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public ArrayList<HinhGhiChu> LayHinhAnhCuaGhiChu(int idCuaGhiChu) {

        ArrayList<HinhGhiChu> arrayList = new ArrayList<>();
        try {
            String query = "SELECT * FROM" + TABLE_NAME_GHICHU_HINH +
                    " WHERE " + COLUMN_GHICHU_HINH_ISXOA + " = FALSE" + " AND " +
                    COLUMN_GHICHU_HINH_IDGHICHU + " = " + idCuaGhiChu;

            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null && cursor.moveToFirst()) {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    int id = Integer.valueOf(cursor.getString(0));
                    int idGhiChu = Integer.valueOf(cursor.getString(1));
                    boolean isXoa = Boolean.valueOf(cursor.getString(2));
                    byte[] image = cursor.getBlob(3);
                    String ngayTao = cursor.getString(4);
                    HinhGhiChu temp = new HinhGhiChu(id, idGhiChu,ngayTao,isXoa,image);
                    arrayList.add(temp);
                }
            }

            return arrayList;
        } catch (Exception ex) {
            Log.e("Lỗi khi lấy ghi chú", ex.getMessage());
            return arrayList;
        }
    }

    public Cursor TimKiemGhiChu(String input) {
        try {
            String query = "SELECT * FROM " + TABLE_NAME_GHICHU + " ORDER BY " + COLUMN_GHICHU_ID + " DESC";
            if (!TextUtils.isEmpty(input)) {
                query = "SELECT * FROM " + TABLE_NAME_GHICHU + " WHERE " + COLUMN_GHICHU_TIEUDE + " like N'%" + input + "%' ORDER BY " + COLUMN_GHICHU_ID + " DESC";
            }

            return db.rawQuery(query, null);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean XoaGhiChu(int Id) {
        try {
            db.delete(TABLE_NAME_GHICHU, COLUMN_GHICHU_ID + " = ?", new String[]{String.valueOf(Id)});
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
            db.execSQL("CREATE TABLE " + TABLE_NAME_GHICHU + " (" +
                    COLUMN_GHICHU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_GHICHU_TIEUDE + " NVARCHAR(500), " +
                    COLUMN_GHICHU_NOIDUNG + " TEXT, " +
                    COLUMN_GHICHU_ISXOA + " BIT, " +
                    //COLUMN_GHICHU_IMAGE + " BLOB," +
                    COLUMN_GHICHU_NGAYTAO + " NVARCHAR(10)" +
                    COLUMN_GHICHU_NGAYSUA + " NVARCHAR(10)" +
                    ");");

            db.execSQL("CREATE TABLE " + TABLE_NAME_GHICHU_HINH + " (" +
                    COLUMN_GHICHU_HINH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_GHICHU_HINH_IDGHICHU + " INTEGER, " +
                    COLUMN_GHICHU_HINH_ISXOA + " BIT, " +
                    COLUMN_GHICHU_HINH_IMAGE + " BLOB," +
                    COLUMN_GHICHU_HINH_NGAYTAO + " NVARCHAR(10)" +
                    ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GHICHU);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GHICHU_HINH);
            onCreate(db);
        }
    }
}
