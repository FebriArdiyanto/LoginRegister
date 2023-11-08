package com.febri.loginregister;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class akunAdapter extends BaseAdapter {

    Activity activity;
    List<data> items;
    private LayoutInflater inflater;

    public akunAdapter(Activity activity, List<data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) convertView = inflater.inflate(R.layout.list, null);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView username = (TextView) convertView.findViewById(R.id.usnm);
        //TextView password = (TextView) convertView.findViewById(R.id.pss);
        TextView nama = (TextView) convertView.findViewById(R.id.nm);
//        TextView lahir = (TextView) convertView.findViewById(R.id.lhr);
//        TextView jenis_kelamin = (TextView) convertView.findViewById(R.id.jk);
//        TextView alamat = (TextView) convertView.findViewById(R.id.lamat);
//        TextView nohp = (TextView) convertView.findViewById(R.id.hp);
//        TextView tb = (TextView) convertView.findViewById(R.id.etbb);
//        TextView bb = (TextView) convertView.findViewById(R.id.ebbb);

        data Data = items.get(position);

        id.setText(Data.getId());
        username.setText(Data.getUsername());
        //password.setText(Data.getPassword());
        nama.setText(Data.getNama_lengkap());
//        lahir.setText(Data.getTanggal_lahir());
//        jenis_kelamin.setText(Data.getJenis_kelamin());
//        alamat.setText(Data.getAlamat());
//        nohp.setText(Data.getNohp());
//        tb.setText(Data.getTb());
//        bb.setText(Data.getBb());

        return convertView;
    }
}
