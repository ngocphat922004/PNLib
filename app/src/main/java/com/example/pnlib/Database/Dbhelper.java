package com.example.pnlib.Database;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pnlib.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper(Context context) {
        super(context,"SACH",null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table NguoiDung(username text primary key,chucvu text,password text, email text)");
        db.execSQL("create table LISTBOOK(masach integer primary key autoincrement," +
                "imagesach blog,tensach text,giasach double,giamuon double,ngonngu text,namsx text,quocgia text,noidung text ,soluong int ,theloai text, nxb text)");
        db.execSQL("create table PHIEUMUON(maphieumuon integer primary key autoincrement," +
                "imagesachmuon blog, tensach text, tennguoimuon text, tennguoinhap text, soluong integer, giamuon double , ngaymuon text, tinhtrang text)");
        db.execSQL("create table THELOAI(matheloai integer primary key autoincrement, tentheloai text)");
        String data ="insert into THELOAI values"+
                "(1,'Tất cả')";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists NguoiDung"); // nếu bảng người dùng tồn tại
        db.execSQL("drop table if exists LISTBOOK");
        db.execSQL("drop table if exists PHIEUMUON");
        db.execSQL("drop table if exists THELOAI");
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    //Hàm tìm tên sách
    public List<Book> Searchbook(String searchbook){
        List<Book> list = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String[] colums = {"masach","imagesach","tensach","giasach","giamuon","ngonngu","namsx","quocgia","noidung","soluong","theloai","nxb"};
        String selection = "tensach" + " LIKE ?";
        String [] selectionArgs = {"%" + searchbook + "%"};
        Cursor cursor = database.query("LISTBOOK",colums,selection,selectionArgs,null,null,null);
        if (cursor.moveToFirst()){
            do {
                int masach = cursor.getInt(0);
                byte[] imagesach = cursor.getBlob(1);
                String tensach = cursor.getString(2);
                Double giasach = cursor.getDouble(3);
                Double giamuon = cursor.getDouble(4);
                String ngonngu = cursor.getString(5);
                String namsx = cursor.getString(6);
                String quocgia = cursor.getString(7);
                String noidung = cursor.getString(8);
                int soluong = cursor.getInt(9);
                String theloai = cursor.getString(10);
                String nxb = cursor.getString(11);
                Book book = new Book(masach, imagesach, tensach, giasach,giamuon,ngonngu ,namsx ,quocgia ,noidung, soluong, theloai, nxb);
                list.add(book);
            }while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }



    // Hàm register
    public  void register(String Username,String chucvu,String Password, String Email){
        ContentValues values = new ContentValues();
        values.put("username",Username);
        values.put("chucvu",chucvu);
        values.put("password",Password);
        values.put("email",Email);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("NguoiDung",null,values);
        database.close();
    }
    //Hàm Login
    public int login(String Username,String Password){
        int result = 0;
        String srt[] = new String[2];
        srt[0] = Username;
        srt[1] = Password;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from NguoiDung where username =? and password =?",srt);
        if (cursor.moveToNext()){ // có tồn tại dữ liệu
            result = 1;
        }
        return result;
    }
    //hàm check username
    public boolean checkUsername(String Username){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from NguoiDung where username =?",
                new String[]{Username});
        if (cursor.getCount() > 0){ // có tồn tại dữ liệu
            return true;
        }else {
            return false;
        }
    }
    //hàm update password
    public boolean updatepassword(String Username,String Password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",Password);
        long result = sqLiteDatabase.update("NguoiDung",values,"username =?",
                new String[]{Username});
        return  result != -1;
    }
}
