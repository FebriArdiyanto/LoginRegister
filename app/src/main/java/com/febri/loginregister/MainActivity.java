package com.febri.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    EditText username;
    TextInputEditText password;
    private CheckBox cb;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";
    Button masuk;
    TextView sdh;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cb = findViewById(R.id.show);
        masuk = findViewById(R.id.masuk);
        sdh = findViewById(R.id.blm);
        DB = new DBHelper(this);

        getPreferencesData();


        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Tolong isikan data dengan benar", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Login sukses", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                if (cb.isChecked()){
                    Boolean boolIsChecked = cb.isChecked();
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("pref_name", user);
                    editor.putString("pref_pass", pass);
                    editor.putBoolean("pref_check", boolIsChecked);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Akun Telah Disimpan", Toast.LENGTH_SHORT).show();
                }else{
                    mPrefs.edit().clear().apply();
                }
                username.getText().clear();
                password.getText().clear();
            }
        });

        sdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
    private void getPreferencesData(){
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u = sp.getString("pref_name", "not found.");
            username.setText(u.toString());
        }
//        if(sp.contains("pref_pass")){
//            String p = sp.getString("pref_pass", "not found.");
//            password.setText(p.toString());
//        }
        if(sp.contains("pref_check")){
            Boolean b = sp.getBoolean("pref_check", false);
            cb.setChecked(b);
        }
    }
}