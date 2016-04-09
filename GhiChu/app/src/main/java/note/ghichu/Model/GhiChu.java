package note.ghichu.Model;

/**
 * Created by tranv on 4/8/2016.
 */
public class GhiChu {
    int Id;
    String TieuDe;
    String NoiDung;
    boolean IsXoa;

    public GhiChu(int id, String tieuDe, String noiDung, boolean isXoa) {
        Id = id;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        IsXoa = isXoa;
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
