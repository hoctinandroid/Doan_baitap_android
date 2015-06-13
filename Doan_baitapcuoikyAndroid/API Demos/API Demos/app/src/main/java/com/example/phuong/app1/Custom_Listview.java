package com.example.phuong.app1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Phuong on 03/04/2015.
 */
public class Custom_Listview extends ArrayAdapter<SinhVien> {
    private Activity context;
    private ArrayList<SinhVien> arr= null;
    int layoutid;

    public Custom_Listview(Activity context, int layoutid, ArrayList<SinhVien> arr){
        super(context,layoutid,arr);
        this.context = context;
        this.arr = arr;
        this.layoutid = layoutid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutid, null);
        if(arr.size()>0 && position>=0){
            final TextView txt = (TextView) convertView.findViewById(R.id.txtInfor);
            final SinhVien sv = arr.get(position);
            txt.setText(sv.toString());
        }
        return convertView;
    }
}
