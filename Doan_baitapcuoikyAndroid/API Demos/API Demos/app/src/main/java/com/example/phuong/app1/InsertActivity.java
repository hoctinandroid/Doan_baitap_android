package com.example.phuong.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Phuong on 25/03/2015.
 */
public class InsertActivity extends Activity {
    QLSV std_manager;
    Button btnInsert;
    Button btnCancel;
    EditText edtMSSV;
    EditText edtHoTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_insert);
        std_manager = new QLSV(this);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtMSSV = (EditText) findViewById(R.id.edtMSSV);
        edtHoTen = (EditText) findViewById(R.id.edtHoTen);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudent(edtMSSV.getText().toString(), edtHoTen.getText().toString());
            sendStudentInforBack(edtMSSV.getText().toString(), edtHoTen.getText().toString());
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_up, R.anim.anim_slide_out_up);
            }
        });
    }

    public void insertStudent(String mssv, String hoten){
        std_manager.addStudents(new SinhVien(mssv, hoten));
    }

    public void sendStudentInforBack(String mssv, String hoten){
        Intent curr = getIntent();
        curr.putExtra("mssv",mssv);
        curr.putExtra("hoten",hoten);
        setResult(2, curr);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
