package com.febri.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class RegisterActivity2 extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    private EditText etusername, etnamleng, etalamat, etnohp, lahir, etb, ebb;
    private TextInputEditText bpassword, etrepass;
    private RadioGroup allgender;
    private RadioButton jenis_kelamin;
    private Button daftar;
    TextView klikdisini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        lahir = findViewById(R.id.tgl);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        etusername = findViewById(R.id.usernm);
        bpassword = findViewById(R.id.pass);
        etrepass = findViewById(R.id.repass);
        etnamleng = findViewById(R.id.nama_lngkp);
        lahir = findViewById(R.id.tgl);
        allgender = findViewById(R.id.radio_grup);
        etalamat = findViewById(R.id.alamat);
        etnohp = findViewById(R.id.nohp);
        etb = findViewById(R.id.tb);
        ebb = findViewById(R.id.bb);
        daftar = findViewById(R.id.daftar);
        klikdisini = findViewById(R.id.sdh);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = allgender.getCheckedRadioButtonId();
                jenis_kelamin = (RadioButton) findViewById(radioid);
                String username = etusername.getText().toString();
                String password = bpassword.getText().toString();
                String namleng = etnamleng.getText().toString();
                String etlahir = lahir.getText().toString();
                String gender = jenis_kelamin.getText().toString();
                String alamat = etalamat.getText().toString();
                String nohp = etnohp.getText().toString();
                String tb = etb.getText().toString();
                String bb = ebb.getText().toString();

                if (!(username.isEmpty() || password.isEmpty() || namleng.isEmpty() || etlahir.isEmpty() || gender.isEmpty() || alamat.isEmpty() || nohp.isEmpty() || tb.isEmpty() || bb.isEmpty())){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlregister, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    {
                        @Override
                        protected HashMap<String, String> getParams() throws AuthFailureError{
                            HashMap<String, String> params = new HashMap<>();

                            params.put("username", username);
                            params.put("password", password);
                            params.put("nama_lengkap", namleng);
                            params.put("tanggal_lahir", etlahir);
                            params.put("jenis_kelamin", gender);
                            params.put("alamat", alamat);
                            params.put("nohp", nohp);
                            params.put("tb", tb);
                            params.put("bb", bb);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }else {
                    Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        klikdisini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity2.this, LoginActivity.class);
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
                lahir.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}