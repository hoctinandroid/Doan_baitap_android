package com.example.phuong.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements Animation.AnimationListener {
    Button btnAdd;
    Button btnDeleteAll;
    Button btnDelete;
    Button btnUpdate;
    QLSV std_manager;
    ListView lv;
    ArrayList<SinhVien> arrStudents = null;
    Custom_Listview adapter = null;
    Animation anime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        std_manager = new QLSV(this);

        setContentView(R.layout.activity_main);


        arrStudents = new ArrayList<SinhVien>();
        lv = (ListView) findViewById(R.id.lvSinhVien);
        resetListView();
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnDoUpdate);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsertActivity();
            }
        });
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                std_manager.deleteAllStudents();
                resetListView();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adl = new AlertDialog.Builder(MainActivity.this);
                adl.setTitle("You will delete all selected row(s)?");
                adl.setMessage("Do you want to continue?");
                adl.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                        resetListView();
                    }
                });
                adl.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Không thì thôi!", Toast.LENGTH_SHORT).show();
                    }
                });
                adl.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = -1;
                int count = 0;
                for(int i=lv.getChildCount() - 1; i >= 0; i--){
                    View view = lv.getChildAt(i);
                    CheckBox ckc = (CheckBox) view.findViewById(R.id.ckcSelect);
                    if(ckc.isChecked()){
                        count++;
                        if(count == 1) pos = i;
                    }
                }
                if(pos == -1) Toast.makeText(getApplicationContext(), "Not choose yet!", Toast.LENGTH_SHORT).show();
                else{
                    if(count > 1) Toast.makeText(getApplicationContext(), "You can't update " +
                            "more than one student at one time!", Toast.LENGTH_SHORT).show();
                    else{
                        openUpdateActivity(arrStudents.get(pos));
                        resetListView();
                    }
                }
            }
        });
    }
    public void delete(){
        for(int i=lv.getChildCount() - 1; i >= 0; i--){
            View view = lv.getChildAt(i);
            CheckBox ckc = (CheckBox) view.findViewById(R.id.ckcSelect);
            if(ckc.isChecked()){
                String mssv = arrStudents.get(i).getMSSV();
                std_manager.delete(mssv);
            }
        }
    }
    public void resetListView(){
        List<SinhVien> new_list = std_manager.getAllStudents();
        lv = (ListView) findViewById(R.id.lvSinhVien);
        arrStudents = new ArrayList<SinhVien>();
        adapter = new Custom_Listview(this, R.layout.custom_listview, arrStudents);
        lv.setAdapter(adapter);
        for(SinhVien s:new_list){
            arrStudents.add(s);
            adapter.notifyDataSetChanged();
        }
    }
    public void openInsertActivity(){
        Intent insertItent;
        insertItent = new Intent(MainActivity.this, InsertActivity.class);
        startActivityForResult(insertItent,1);
        overridePendingTransition(R.anim.anim_slide_in_down, R.anim.anim_slide_out_down);
    }

    public void openUpdateActivity(SinhVien sv){

        Intent it = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bd = new Bundle();
        bd.putSerializable("sv",sv);
        it.putExtra("sinhvien",bd);
        startActivityForResult(it, 1);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){
            resetListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
