package note.ghichu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class CapNhatActivity extends AppCompatActivity {

    private int IdDangChon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IdDangChon = LayDuLieuCuaItemDangChon();
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTrangCapNhatGhiChu);
        if (fab != null) {
            final int finalIdDangChon = IdDangChon;
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean ketqua = CapNhatGhiChu(finalIdDangChon);
                    if (ketqua) {
                        HienThiThongBao("Cập nhật thành công");
                        finish();
                    } else {
                        HienThiThongBao("Cập nhật thất bại");
                    }
                }
            });
        }
    }

    private void HienThiThongBao(String noidung) {
        Toast.makeText(getApplicationContext(), noidung, Toast.LENGTH_SHORT).show();
    }

    private int LayDuLieuCuaItemDangChon() {
        Intent intent = getIntent();
        Bundle ghiChuBundle = intent.getBundleExtra("GhiChu");
        int id = ghiChuBundle.getInt("Id");
        String tieuDe = ghiChuBundle.getString("TieuDe");
        String noiDung = ghiChuBundle.getString("NoiDung");

        /*DBGhiChu dbGhiChu = new DBGhiChu(this);
        dbGhiChu.OpenConnection();
        ArrayList<HinhGhiChu> hinhCuaGhiChu = dbGhiChu.LayHinhAnhCuaGhiChu(id);*/

        EditText etNoiDung = (EditText) findViewById(R.id.etNoiDung);
        EditText etTieuDe = (EditText) findViewById(R.id.etTieuDe);
        if (etNoiDung != null) {
            etNoiDung.setText(noiDung);
        }
        if (etTieuDe != null) {
            etTieuDe.setText(tieuDe);
        }
        return id;
    }

    private boolean CapNhatGhiChu(int id) {
        EditText etNoiDung = (EditText) findViewById(R.id.etNoiDung);
        EditText etTieuDe = (EditText) findViewById(R.id.etTieuDe);
        String tieuDe = "";
        if (etTieuDe != null) {
            tieuDe = etTieuDe.getText().toString();
        }
        String noiDung = "";
        if (etNoiDung != null) {
            noiDung = etNoiDung.getText().toString();
        }

        if (tieuDe.equals("")) {
            if (etTieuDe != null) {
                etTieuDe.requestFocus();
            }
            HienThiThongBao("Tiêu đề không bỏ trống");
            return false;
        }

        if (noiDung.equals("")) {
            if (etNoiDung != null) {
                etNoiDung.requestFocus();
            }
            HienThiThongBao("Nội dung không bỏ trống");
            return false;
        }

        Bitmap b = BitmapFactory.decodeResource(getResources(),R.id.ivKhungChupAnh);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        //byte[] img = bos.toByteArray();

        DBGhiChu dbGhiChu = new DBGhiChu(this);
        dbGhiChu.OpenConnection();

        GhiChu ghiChuMoi = new GhiChu(0, "", "", false, "", "");
        ghiChuMoi.setTieuDe(tieuDe);
        ghiChuMoi.setNoiDung(noiDung);
        ghiChuMoi.setIsXoa(false);
        //ghiChuMoi.setImageByte(img);
        boolean ketqua = dbGhiChu.CapNhatGhiChu(id, ghiChuMoi);

        dbGhiChu.CloseConnection();
        return ketqua;
    }

    private boolean XoaGhiChu(int id) {
        DBGhiChu dbGhiChu = new DBGhiChu(this);
        dbGhiChu.OpenConnection();

        boolean ketqua = dbGhiChu.XoaGhiChu(id);

        dbGhiChu.CloseConnection();
        return ketqua;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_capnhat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            final int finalIdDangChon = IdDangChon;
            new AlertDialog.Builder(CapNhatActivity.this)
                    .setMessage("Bạn có muốn xóa không :v?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            boolean ketqua = XoaGhiChu(finalIdDangChon);
                            if (ketqua) {
                                HienThiThongBao("Cập nhật thành công");
                                finish();
                            } else {
                                HienThiThongBao("Cập nhật thất bại");
                            }
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
