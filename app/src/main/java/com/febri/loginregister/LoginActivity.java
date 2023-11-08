package com.febri.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText eyusername;
    private TextInputEditText aypassword;
    private Button pmasuk;
    TextView sdh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eyusername = findViewById(R.id.username);
        aypassword = findViewById(R.id.password);
        pmasuk = findViewById(R.id.masuk);
        sdh = findViewById(R.id.blm);

        pmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = eyusername.getText().toString();
                String password = aypassword.getText().toString();

                if (!(username.isEmpty() || password.isEmpty())){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, Db_Contract.urllogin + "?username=" + username + "&password=" + password, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("Selamat Datang")){

                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity2.class);
                startActivity(intent);
            }
        });
    }
}