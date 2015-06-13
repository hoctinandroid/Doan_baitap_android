package com.example.phuong.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Phuong on 03/04/2015.
 */
public class UpdateActivity extends Activity {
    QLSV std_manager;
    Button btnUpdate, btnCancel;
    EditText edtHoten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        std_manager = new QLSV(this);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtHoten = (EditText) findViewById(R.id.edtHoTen);
        final Intent caller = getIntent();
        Bundle bd = caller.getBundleExtra("sinhvien");
        final SinhVien sv = (SinhVien) bd.getSerializable("sv");
        edtHoten.setText(sv.getHoten());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                std_manager.update(new SinhVien(sv.getMSSV(), edtHoten.getText().toString()));
                setResult(2, caller);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });
    }
}
