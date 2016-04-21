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
    String DateString;
    boolean IsXoa;
    byte[] ImageByte;

    public GhiChu(int id, String tieuDe, String noiDung, boolean isXoa, byte[] imageByte, String dateString) {
        Id = id;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        IsXoa = isXoa;
        if(dateString != ""){
            DateString = dateString;
        } else{
            DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
            Date date = new Date();
            DateString = dateFormat.format(date);
        }

        ImageByte= imageByte;
    }

    public String getDateString() {
        return DateString;
    }

    public void setDateString(String dateString) {
        DateString = dateString;
    }

    public byte[] getImageByte() {
        return ImageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.ImageByte = imageByte;
    }

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
