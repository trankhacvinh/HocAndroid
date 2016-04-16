package note.ghichu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class CapNhatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);

        int IdDangChon = 0;

        IdDangChon = LayDuLieuCuaItemDangChon();

        Button bCapNhat = (Button) findViewById(R.id.bCapNhatGhiChu);
        if (bCapNhat != null) {
            final int finalIdDangChon = IdDangChon;
            bCapNhat.setOnClickListener(new View.OnClickListener() {
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
        Button bXoa = (Button) findViewById(R.id.bXoaGhiChu);
        if (bXoa != null) {
            final int finalIdDangChon = IdDangChon;
            bXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        byte[] img = bos.toByteArray();

        DBGhiChu dbGhiChu = new DBGhiChu(this);
        dbGhiChu.OpenConnection();

        GhiChu ghiChuMoi = new GhiChu(0, "", "", false, new byte[0]);
        ghiChuMoi.setTieuDe(tieuDe);
        ghiChuMoi.setNoiDung(noiDung);
        ghiChuMoi.setIsXoa(false);
        ghiChuMoi.setImageByte(img);
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
}
