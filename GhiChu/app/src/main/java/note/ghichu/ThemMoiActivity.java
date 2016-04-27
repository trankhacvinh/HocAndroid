package note.ghichu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class ThemMoiActivity extends AppCompatActivity {
    private ImageView capturedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themmoi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabTrangThemMoiGhiChu);
        fab.setOnClickListener(new View.OnClickListener() {
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

        Button bThemMoiHinhAnh = (Button) findViewById(R.id.bThemMoiHinhAnh);
        if(bThemMoiHinhAnh!=null){
            bThemMoiHinhAnh.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    ThemImageViewVaoGhiChu();
                }
            });
        }
    }

    private void ThemImageViewVaoGhiChu() {
        try {
            GridLayout gridLayout = (GridLayout) findViewById(R.id.glHinhAnhCuaGhiChu);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.azusa);
            imageView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            if (gridLayout != null) {
                imageView.setId(gridLayout.getChildCount());
                gridLayout.addView(imageView);
            }
        }
        catch (Exception ex){
            Log.e("wtf",ex.getMessage());
        }
    }

    private void openCamera() {
        PackageManager packageManager = this.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(getApplicationContext(), "Thiết bị không hỗ trợ camera.", Toast.LENGTH_SHORT).show();
        }

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
       /*
       byte[] img = new byte[0];

        if(capturedImage != null && capturedImage.getDrawable() != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Bitmap b = ((BitmapDrawable) capturedImage.getDrawable()).getBitmap();
            b.compress(Bitmap.CompressFormat.PNG, 100, bos);
            img = bos.toByteArray();
        }
        */
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
        GhiChu ghiChuMoi = new GhiChu(0, "", "", false, "", "");
        ghiChuMoi.setTieuDe(tieuDeValue);
        ghiChuMoi.setNoiDung(noiDungValue);
        ghiChuMoi.setIsXoa(false);
        //ghiChuMoi.setImageByte(img);
        boolean ketqua = dbGhiChu.ThemGhiChu(ghiChuMoi);
        dbGhiChu.CloseConnection();
        return ketqua;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themmoi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cancel) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
