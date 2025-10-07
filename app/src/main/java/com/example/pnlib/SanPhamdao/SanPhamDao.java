package com.example.pnlib.SanPhamdao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pnlib.Database.Dbhelper;
import com.example.pnlib.Model.Book;
import com.example.pnlib.Model.PhieuMuon;
import com.example.pnlib.Model.Theloai;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    private  final Dbhelper dbhelper;

    public SanPhamDao(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<Book> getlistbook(){
        ArrayList<Book> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("select * from LISTBOOK",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new Book(cursor.getInt(0),
                            cursor.getBlob(1),
                            cursor.getString(2),
                            cursor.getDouble(3),
                            cursor.getDouble(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getInt(9),
                            cursor.getString(10),
                            cursor.getString(11)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
;            }
        }catch (Exception e){
            Log.e("Error","getlistbook"+e);
        }finally {
            database.endTransaction();
        }
        return list;
    }

    public ArrayList<Theloai> getlisttheloai(){
        ArrayList<Theloai> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("select * from THELOAI",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    list.add(new Theloai(cursor.getInt(0),
                            cursor.getString(1)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Error","getlisttheloai"+e);
        }finally {
            database.endTransaction();
        }
        return list;

    }

    public String Timkiemchucvu(String Username){
        String chucvu = null;
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("select chucvu from NguoiDung where username = ?",new String[]{Username});
                if (cursor.getCount() > 0){
                    cursor.moveToFirst();
                    chucvu = cursor.getString(0);
                }
                database.setTransactionSuccessful();
        }catch (Exception e){
            Log.e("Erorr","getchucvunguoidung"+e);
        }finally {
            database.endTransaction();
        }
        return chucvu;
    }


    //hàm add book
    public boolean addbook (Book book){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagesach",book.getImagesach());
        values.put("tensach",book.getTensach());
        values.put("giasach",book.getGiasach());
        values.put("giamuon",book.getGiamuon());
        values.put("ngonngu",book.getNgonngu());
        values.put("namsx",book.getNamsx());
        values.put("quocgia",book.getQuocgia());
        values.put("noidung",book.getNoidung());
        values.put("soluong",book.getSoluong());
        values.put("theloai",book.getTheloai());
        values.put("nxb",book.getNxb());
        long check = database.insert("LISTBOOK",null,values);
        return check != -1;
    }
    //Hàm delete
    public boolean delete(int masach){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        long check = database.delete("LISTBOOK","masach=?",new String[]{String.valueOf(masach)});
        return check != -1;
    }
    public boolean updatebook(Book book){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagesach",book.getImagesach());
        values.put("tensach",book.getTensach());
        values.put("giasach",book.getGiasach());
        values.put("giamuon",book.getGiamuon());
        values.put("ngonngu",book.getNgonngu());
        values.put("namsx",book.getNamsx());
        values.put("quocgia",book.getQuocgia());
        values.put("noidung",book.getNoidung());
        values.put("soluong",book.getSoluong());
        values.put("theloai",book.getTheloai());
        values.put("nxb",book.getNxb());
        long check = database.update("LISTBOOK",values,"masach=?",
                new String[]{String.valueOf(book.getMasach())});
        return check != -1;
    }

    //hàm add phiếu mượn
    public boolean addphieumuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagesachmuon",phieuMuon.getImagesachmuon());
        values.put("tensach",phieuMuon.getTensach());
        values.put("tennguoimuon",phieuMuon.getTennguoimuon());
        values.put("tennguoinhap",phieuMuon.getTennguoinhap());
        values.put("soluong",phieuMuon.getSoluong());
        values.put("giamuon",phieuMuon.getGiamuon());
        values.put("ngaymuon",phieuMuon.getNgaymuon());
        values.put("tinhtrang",phieuMuon.getTinhtrang());


        long check = database.insert("PHIEUMUON",null,values);
        return check != -1;
    }

    public boolean addtheloai(Theloai theloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tentheloai",theloai.getTentheloai());

        long check = sqLiteDatabase.insert("THELOAI",null,values);
        return check != -1;
    }

    public ArrayList<PhieuMuon> getlistphieumuon(){
        ArrayList<PhieuMuon> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("select * from PHIEUMUON",null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    list.add(new PhieuMuon(cursor.getInt(0),
                            cursor.getBlob(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5),
                            cursor.getDouble(6),
                            cursor.getString(7),
                            cursor.getString(8)));
                }while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Error","getlistphieumuon"+e);
        }finally {
            database.endTransaction();
        }
        return list;
    }
    //Hàm tìm kiếm tên thể loại
        public List<Book> Searchtheloai(String searchtheloai){
        List<Book> list = new ArrayList<>();
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        String[] colums = {"masach","imagesach","tensach","giasach","giamuon","ngonngu","namsx","quocgia","noidung","soluong","theloai","nxb"};
        String selection = "theloai  like ?";
        String[] selectionArgs = {"%" +searchtheloai +"%"};
        Cursor cursor = database.query("LISTBOOK",colums,selection,selectionArgs,null,null,null,null);
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

    //hàm check theloai
    public String checktheloai(String theloai){
        String nametheloai = "";
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        database.beginTransaction();
        try{
            Cursor cursor = database.rawQuery("select tentheloai from THELOAI where tentheloai=?",
                    new String[]{theloai});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                nametheloai = cursor.getString(0);
                database.setTransactionSuccessful();
            }
        }catch (Exception e){
            Log.e("Erorr","getchucvunguoidung"+e);
        }finally {
            database.endTransaction();
        }
        return nametheloai;


    }
    //hàm kiểm tra số lượng thể loại
    public int getnumbertheloai(){
        SQLiteDatabase database = dbhelper.getReadableDatabase();
        String query = "select count(*) from THELOAI";
        Cursor cursor = database.rawQuery(query,null);

        int count = 0;
        if (cursor!= null){
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
    }
    // Hàm update phiếu mượn
    public boolean updatephieumuon(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagesachmuon",phieuMuon.getImagesachmuon());
        values.put("tensach",phieuMuon.getTensach());
        values.put("tennguoimuon",phieuMuon.getTennguoimuon());
        values.put("tennguoinhap",phieuMuon.getTennguoinhap());
        values.put("soluong",phieuMuon.getSoluong());
        values.put("giamuon",phieuMuon.getGiamuon());
        values.put("ngaymuon",phieuMuon.getNgaymuon());
        values.put("tinhtrang",phieuMuon.getTinhtrang());


        long check = database.update("PHIEUMUON",values,"maphieumuon=?",
                new String[]{String.valueOf(phieuMuon.getMaphieumuon())});
        return check != -1;
    }

}
