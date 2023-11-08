package com.febri.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "user.db";
    public DBHelper(Context context){super(context, "user.db", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Users(username TEXT primary key, password TEXT, konfirmasi_password TEXT, " +
                "nama_lengkap TEXT, tgl_lahir TEXT, jenis_kelamin TEXT, tb TEXT, bb TEXT, nohp TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists Users");

    }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Users where username = ?", new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertData(String username, String password, String konfirmasi_password, String nama_lengkap, String tgl_lahir, String jenis_kelamin, String tb, String bb, String nohp) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("konfirmasi_password", konfirmasi_password);
        contentValues.put("nama_lengkap", nama_lengkap);
        contentValues.put("tgl_lahir", tgl_lahir);
        contentValues.put("jenis_kelamin", jenis_kelamin);
        contentValues.put("tb", tb);
        contentValues.put("bb", bb);
        contentValues.put("nohp", nohp);
        long result = MyDB.insert("users", null, contentValues);
        if (result==-1)return false;
        else
            return true;
    }
    public Cursor viewData(String username){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Users where username = ?", new String[]{username});
        return cursor;
    }
}
