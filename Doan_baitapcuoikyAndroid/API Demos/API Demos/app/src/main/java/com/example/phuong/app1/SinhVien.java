package com.example.phuong.app1;

import java.io.Serializable;

/**
 * Created by Phuong on 02/04/2015.
 */
public class SinhVien implements Serializable {
    private String Hoten;
    private String MSSV;
    public SinhVien(){};
    public SinhVien(String mssv, String hoten){
        Hoten = hoten;
        MSSV = mssv;
    }
    public String getHoten(){
        return Hoten;
    }
    public String getMSSV(){
        return MSSV;
    }
    public void setHoten(String hoten){
        Hoten = hoten;
    }
    public void setMSSV(String mssv){
        MSSV = mssv;
    }

    @Override
    public String toString() {
        return "Họ tên: "+Hoten+" - MSSV: "+MSSV;
    }
}
