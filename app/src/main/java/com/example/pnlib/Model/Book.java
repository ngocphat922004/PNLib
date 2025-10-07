package com.example.pnlib.Model;

public class Book {
    int masach;
    byte[] imagesach;
    String tensach;
    Double giasach;
    Double giamuon;
    String ngonngu;
    String namsx;
    String quocgia;
    String noidung;
    int soluong;
    String theloai;
    String nxb;


    public Book(int masach, byte[] imagesach, String tensach, Double giasach, Double giamuon, String ngonngu, String namsx, String quocgia, String noidung, int soluong, String theloai, String nxb) {
        this.masach = masach;
        this.imagesach = imagesach;
        this.tensach = tensach;
        this.giasach = giasach;
        this.giamuon = giamuon;
        this.ngonngu = ngonngu;
        this.namsx = namsx;
        this.quocgia = quocgia;
        this.noidung = noidung;
        this.soluong = soluong;
        this.theloai = theloai;
        this.nxb = nxb;
    }

    public Book() {
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public byte[] getImagesach() {
        return imagesach;
    }

    public void setImagesach(byte[] imagesach) {
        this.imagesach = imagesach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public Double getGiasach() {
        return giasach;
    }

    public void setGiasach(Double giasach) {
        this.giasach = giasach;
    }

    public Double getGiamuon() {
        return giamuon;
    }

    public void setGiamuon(Double giamuon) {
        this.giamuon = giamuon;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public String getNamsx() {
        return namsx;
    }

    public void setNamsx(String namsx) {
        this.namsx = namsx;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }
}