package note.ghichu.Model;

/**
 * Created by tranv on 4/8/2016.
 */
public class GhiChu {
    int Id;
    String TieuDe;
    String NoiDung;
    boolean IsXoa;
    byte[] ImageByte;

    public GhiChu(int id, String tieuDe, String noiDung, boolean isXoa, byte[] imageByte) {
        Id = id;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        IsXoa = isXoa;
        ImageByte= imageByte;
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
