package note.ghichu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import note.ghichu.Model.DBGhiChu;
import note.ghichu.Model.GhiChu;

public class ThemMoiActivity extends AppCompatActivity {

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
                    if(ketqua){
                        HienThiThongBao("Thêm mới thành công");
                        finish();
                    } else{
                        HienThiThongBao("Thêm mới thất bại");
                    }
                }
            });
        }
    }

    private void HienThiThongBao(String noidung){
        Toast.makeText(getApplicationContext(),noidung,Toast.LENGTH_SHORT).show();
    }

    private boolean ThemMoiGhiChu(){
        DBGhiChu dbGhiChu = new DBGhiChu(this);
        EditText tieuDe = (EditText) findViewById(R.id.etTieuDe);
        EditText noiDung = (EditText) findViewById(R.id.etNoiDung);
        String tieuDeValue = "";
        String noiDungValue = "";

        if (tieuDe != null) {
            tieuDeValue = tieuDe.getText().toString();
            if(tieuDeValue.equals("")){
                tieuDe.requestFocus();
                HienThiThongBao("Tiêu đề không bỏ trống");
                return false;
            }
        }
        if (noiDung != null) {
            noiDungValue = noiDung.getText().toString();
            if(noiDungValue.equals("")){
                noiDung.requestFocus();
                HienThiThongBao("Nội dung không bỏ trống");
                return false;
            }
        }
        dbGhiChu.OpenConnection();
        GhiChu ghiChuMoi = new GhiChu(0,"","",false);
        ghiChuMoi.setTieuDe(tieuDeValue);
        ghiChuMoi.setNoiDung(noiDungValue);
        ghiChuMoi.setIsXoa(false);
        boolean ketqua = dbGhiChu.ThemGhiChu(ghiChuMoi);
        dbGhiChu.CloseConnection();
        return ketqua;
    }
}
