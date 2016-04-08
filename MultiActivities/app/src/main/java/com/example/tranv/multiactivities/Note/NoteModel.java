package com.example.tranv.multiactivities.Note;

public class NoteModel {
    int id;
    String tieuDe;
    String noiDung;

    public NoteModel() {

    }

    public NoteModel(int id, String tieuDe, String noiDung) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

}
