package com.example.pnlib.Model;

public class PhieuMuon {
    int maphieumuon;
    byte[] imagesachmuon;
    String tensach;
    String tennguoimuon;
    String tennguoinhap;
    int soluong;
    Double giamuon;
    String ngaymuon;
    String tinhtrang;


    public PhieuMuon(int maphieumuon, byte[] imagesachmuon, String tensach, String tennguoimuon, String tennguoinhap, int soluong, Double giamuon, String ngaymuon, String tinhtrang) {
        this.maphieumuon = maphieumuon;
        this.imagesachmuon = imagesachmuon;
        this.tensach = tensach;
        this.tennguoimuon = tennguoimuon;
        this.tennguoinhap = tennguoinhap;
        this.soluong = soluong;
        this.giamuon = giamuon;
        this.ngaymuon = ngaymuon;
        this.tinhtrang = tinhtrang;
    }

    public PhieuMuon() {
    }

    public int getMaphieumuon() {
        return maphieumuon;
    }

    public void setMaphieumuon(int maphieumuon) {
        this.maphieumuon = maphieumuon;
    }

    public byte[] getImagesachmuon() {
        return imagesachmuon;
    }

    public void setImagesachmuon(byte[] imagesachmuon) {
        this.imagesachmuon = imagesachmuon;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTennguoimuon() {
        return tennguoimuon;
    }

    public void setTennguoimuon(String tennguoimuon) {
        this.tennguoimuon = tennguoimuon;
    }

    public String getTennguoinhap() {
        return tennguoinhap;
    }

    public void setTennguoinhap(String tennguoinhap) {
        this.tennguoinhap = tennguoinhap;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Double getGiamuon() {
        return giamuon;
    }

    public void setGiamuon(Double giamuon) {
        this.giamuon = giamuon;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
