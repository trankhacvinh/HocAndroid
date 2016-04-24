package note.ghichu.Model;

/**
 * Created by tranv on 4/23/2016.
 */
public class HinhGhiChu {
    int Id;
    int IdGhiChu;
    String NgayTao;
    boolean IsXoa;
    byte[] ImageByte;


    public HinhGhiChu(int id, int idGhiChu, String ngayTao, boolean isXoa, byte[] imageByte) {
        Id = id;
        IdGhiChu = idGhiChu;
        NgayTao = ngayTao;
        IsXoa = isXoa;
        ImageByte = imageByte;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdGhiChu() {
        return IdGhiChu;
    }

    public void setIdGhiChu(int idGhiChu) {
        IdGhiChu = idGhiChu;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public boolean isXoa() {
        return IsXoa;
    }

    public void setXoa(boolean xoa) {
        IsXoa = xoa;
    }

    public byte[] getImageByte() {
        return ImageByte;
    }

    public void setImageByte(byte[] imageByte) {
        ImageByte = imageByte;
    }

}
