package note.ghichu.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tranv on 4/8/2016.
 */
public class GhiChu {
    int Id;
    String TieuDe;
    String NoiDung;
    String NgayTao;
    String NgaySua;
    boolean IsXoa;
//    byte[] ImageByte;

    public GhiChu(int id, String tieuDe, String noiDung, boolean isXoa, String ngayTao, String ngaySua) {
        Id = id;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        IsXoa = isXoa;
        if(ngayTao != ""){
            NgayTao = ngayTao;
        } else{
            DateFormat dateFormat = new SimpleDateFormat("hh:mm dd MMM yyyy");
            Date date = new Date();
            NgayTao = dateFormat.format(date);
        }
        NgaySua = ngaySua;

//        ImageByte= imageByte;
    }

    public String getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(String ngaySua) {
        NgaySua = ngaySua;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

/*    public byte[] getImageByte() {
        return ImageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.ImageByte = imageByte;
    }*/

    public boolean getIsXoa() {
        return IsXoa;
    }

    public void setIsXoa(boolean isXoa) {
        IsXoa = isXoa;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
