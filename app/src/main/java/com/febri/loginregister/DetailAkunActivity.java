package com.febri.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailAkunActivity extends AppCompatActivity {

    TextView idtext, usnmtext, psstext, nmtext, lhrtext, jktext, lamattext, nohptext, tbtext, bbtext;
    String id, username, password, nama_lengkap, tanggal_lahir, jenis_kelamin, alamat, nohp, tb, bb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);

        idtext = findViewById(R.id.idd);
        usnmtext = findViewById(R.id.usnmd);
        psstext = findViewById(R.id.pssd);
        nmtext = findViewById(R.id.nmd);
        lhrtext = findViewById(R.id.lhrd);
        jktext = findViewById(R.id.jkd);
        lamattext = findViewById(R.id.lamatd);
        nohptext = findViewById(R.id.hpd);
        tbtext = findViewById(R.id.etbbd);
        bbtext = findViewById(R.id.ebbbd);

        id = getIntent().getStringExtra("id");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        nama_lengkap = getIntent().getStringExtra("nama_lengkap");
        tanggal_lahir = getIntent().getStringExtra("tanggal_lahir");
        jenis_kelamin = getIntent().getStringExtra("jenis_kelamin");
        alamat = getIntent().getStringExtra("alamat");
        nohp = getIntent().getStringExtra("nohp");
        tb = getIntent().getStringExtra("tb");
        bb = getIntent().getStringExtra("bb");

        idtext.setText(id);
        usnmtext.setText(username);
        psstext.setText(password);
        nmtext.setText(nama_lengkap);
        lhrtext.setText(tanggal_lahir);
        jktext.setText(jenis_kelamin);
        lamattext.setText(alamat);
        nohptext.setText(nohp);
        tbtext.setText(tb);
        bbtext.setText(bb);

    }
}