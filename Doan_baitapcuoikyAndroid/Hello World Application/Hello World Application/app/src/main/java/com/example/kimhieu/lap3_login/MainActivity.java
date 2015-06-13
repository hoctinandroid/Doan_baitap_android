package com.example.kimhieu.lap3_login;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener{
    // Widget Button Declare
    Button btnLoginDialog;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Widget Button and set click listener
        btnLoginDialog = (Button) findViewById(R.id.btnLoginDialog);
        btnLoginDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLoginDialog) {

            // Create Object of Dialog class
            final Dialog login = new Dialog(this);
            // Set GUI of login screen

            login.setContentView(R.layout.login_layout);
            login.setTitle("Login");
            

            // Init button of login GUI
            Button btnLogin = (Button) login.findViewById(R.id.btnLogin);
            Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
            final EditText txtUsername = (EditText)login.findViewById(R.id.txtUsername);
            final EditText txtPassword = (EditText)login.findViewById(R.id.txtPassword);

            // Attached listener for login GUI button
            btnLogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txtUsername.getText().toString().trim().length() > 0 && txtPassword.getText().toString().trim().length() > 0)
                    {
                        // Validate Your login credential here than display message
                        Toast.makeText(MainActivity.this,
                                "Hello Word!", Toast.LENGTH_LONG).show();

                        // Redirect to dashboard / home screen.
                        login.dismiss();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,
                                "Nhập Username và Password", Toast.LENGTH_LONG).show();

                    }
                }
            });
            btnCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    login.dismiss();
                }
            });

            // Make dialog box visible.
            login.show();
        }
    }

}
