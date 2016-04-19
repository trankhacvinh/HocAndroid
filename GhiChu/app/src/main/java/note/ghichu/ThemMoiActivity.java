package note.ghichu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class ThemMoiActivity extends AppCompatActivity {
    private ImageView capturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);

        Button bThemMoiGhiChu = (Button) findViewById(R.id.bThemMoiGhiChu);
        if (bThemMoiGhiChu != null) {
            bThemMoiGhiChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean ketqua = ThemMoiGhiChu();
                    if (ketqua) {
                        HienThiThongBao("Thêm mới thành công");
                        finish();
                    } else {
                        HienThiThongBao("Thêm mới thất bại");
                    }
                }
            });
        }

        capturedImage = (ImageView) findViewById(R.id.ivKhungChupAnh);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Button btnCamera = (Button) findViewById(R.id.bCamera);
        if (btnCamera != null) {
            btnCamera.setTypeface(font);

            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCamera();
                }
            });
        }
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            capturedImage.setImageBitmap(bp);
        }
    }

    private void HienThiThongBao(String noidung) {
        Toast.makeText(getApplicationContext(), noidung, Toast.LENGTH_SHORT).show();
    }

    private boolean ThemMoiGhiChu() {
        DBGhiChu dbGhiChu = new DBGhiChu(this);
        EditText tieuDe = (EditText) findViewById(R.id.etTieuDe);
        EditText noiDung = (EditText) findViewById(R.id.etNoiDung);
        String tieuDeValue = "";
        String noiDungValue = "";

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Bitmap b = ((BitmapDrawable) capturedImage.getDrawable()).getBitmap();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] img = bos.toByteArray();

        if (tieuDe != null) {
            tieuDeValue = tieuDe.getText().toString();
            if (tieuDeValue.equals("")) {
                tieuDe.requestFocus();
                HienThiThongBao("Tiêu đề không bỏ trống");
                return false;
            }
        }
        if (noiDung != null) {
            noiDungValue = noiDung.getText().toString();
            if (noiDungValue.equals("")) {
                noiDung.requestFocus();
                HienThiThongBao("Nội dung không bỏ trống");
                return false;
            }
        }
        dbGhiChu.OpenConnection();
        GhiChu ghiChuMoi = new GhiChu(0, "", "", false, new byte[0]);
        ghiChuMoi.setTieuDe(tieuDeValue);
        ghiChuMoi.setNoiDung(noiDungValue);
        ghiChuMoi.setIsXoa(false);
        ghiChuMoi.setImageByte(img);
        boolean ketqua = dbGhiChu.ThemGhiChu(ghiChuMoi);
        dbGhiChu.CloseConnection();
        return ketqua;
    }
}
