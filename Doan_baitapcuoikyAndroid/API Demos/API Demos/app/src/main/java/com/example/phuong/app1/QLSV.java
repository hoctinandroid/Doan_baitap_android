package com.example.phuong.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Phuong on 02/04/2015.
 */
public class QLSV extends SQLiteOpenHelper {
    private static final int    DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "StudentManagement";
    private static final String table_name = "Students";
    private static final String KEY_ID = "_id";
    private static final String KEY_HOTEN = "hoten";

    public QLSV(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+table_name+"("+KEY_ID+" TEXT PRIMARY KEY, "+KEY_HOTEN+" TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void addStudents(SinhVien sv){
        SQLiteDatabase write_db = this.getWritableDatabase();
        String nullColumnHack = null;
        ContentValues value = new ContentValues();
        if(!(sv.getHoten().equals(""))&&!(sv.getMSSV().equals(""))){
            value.put(KEY_ID,sv.getMSSV());
            value.put(KEY_HOTEN, sv.getHoten());
            write_db.insert(table_name, nullColumnHack, value);
        }
        write_db.close();
    }

    public List<SinhVien> getAllStudents(){
        List<SinhVien> list = new ArrayList<SinhVien>();
        String[] list_conditions = null;
        String query = "SELECT * FROM "+table_name;
        SQLiteDatabase read_db = this.getReadableDatabase();
        Cursor c = read_db.rawQuery(query, list_conditions);
        if(c.moveToFirst()){
            do{
                SinhVien sv = new SinhVien(c.getString(0), c.getString(1));
                list.add(sv);
            }while (c.moveToNext());
        }
        return list;
    }

    public void deleteAllStudents(){
        SQLiteDatabase write_db = this.getWritableDatabase();
        String where = null;
        String[] whereArgs = null;
        write_db.delete(table_name, where, whereArgs);
    }

    public void delete(String mssv){
        SQLiteDatabase write_db = this.getWritableDatabase();
        String where = "_id=?";
        String[] whereArgs = {String.valueOf(mssv)};
        write_db.delete(table_name, where, whereArgs);
    }

    public int update(SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY_HOTEN, sv.getHoten());
        String where = KEY_ID+"=?";
        String[] whereArgs = {String.valueOf(sv.getMSSV())};
        return db.update(table_name, value, where, whereArgs);
    }

    public SinhVien getStudentByID(String mssv){
        SQLiteDatabase read_db = this.getReadableDatabase();
        String[] comlumn = {KEY_ID, KEY_HOTEN};
        String where = KEY_ID +"=?";
        String[] list_conditions = {String.valueOf("_id")};
        String group_by = null;
        String having = null;
        String order_by = null;
        Cursor c = read_db.query(table_name, comlumn, where, list_conditions, group_by, having, order_by);
        if(c != null) c.moveToFirst();
        SinhVien sv = new SinhVien(c.getString(0), c.getString(1));
        return sv;
    }
}
