package com.febri.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    EditText username, nama_lengkap, tgl_lahir, tb, bb, nohp;
    TextInputEditText password, konfirmasi_password;
    RadioGroup allgender;
    RadioButton jenis_kelamin;
    Button daftar;
    TextView klikdisini;
    DBHelper DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        tgl_lahir = findViewById(R.id.tgl);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        username = (EditText) findViewById(R.id.usernm);
        password = (TextInputEditText) findViewById(R.id.pass);
        konfirmasi_password = (TextInputEditText) findViewById(R.id.repass);
        nama_lengkap = (EditText) findViewById(R.id.nama_lngkp);
        tgl_lahir = (EditText) findViewById(R.id.tgl);
        allgender = (RadioGroup) findViewById(R.id.radio_grup);
        tb = (EditText) findViewById(R.id.tb);
        bb = (EditText) findViewById(R.id.bb);
        nohp = (EditText) findViewById(R.id.nohp);
        daftar = (Button) findViewById(R.id.daftar);
        klikdisini = (TextView) findViewById(R.id.sdh);
        DB = new DBHelper(this);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = allgender.getCheckedRadioButtonId();
                jenis_kelamin = (RadioButton) findViewById(radioid);
                boolean isEmpty = allgender.getCheckedRadioButtonId() == View.NO_ID;
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = konfirmasi_password.getText().toString();
                String nm = nama_lengkap.getText().toString();
                String tgl = tgl_lahir.getText().toString();
                String gender = jenis_kelamin.getText().toString();
                String tingban = tb.getText().toString();
                String berban = bb.getText().toString();
                String hp = nohp.getText().toString();



                if(user.equals(" ") || pass.equals(" ") || repass.equals(" ") || nm.equals(" ") || tgl.equals(" ") || tingban.equals(" ") || berban.equals(" ") || hp.equals(" "))
                    Toast.makeText(RegisterActivity.this, "Mohon Isikan Data Dengan Benar", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass, repass, nm, tgl, gender, tingban, berban, hp);
                            if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "User Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        klikdisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                tgl_lahir.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}