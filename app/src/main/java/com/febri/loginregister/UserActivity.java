package com.febri.loginregister;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    //public static final String URL = "http://192.168.18.100/login_android/select.php";
    ListView list;
    SwipeRefreshLayout swipe;
    List<data> itemList = new ArrayList<data>();
    akunAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.listview);

        adapter = new akunAdapter(UserActivity.this, itemList);
        list.setAdapter(adapter);

//        callVolley();

        try {
            swipe.setOnRefreshListener(this);

            swipe.post(new Runnable() {
                @Override
                public void run() {
                    swipe.setRefreshing(true);
                    itemList.clear();
                    adapter.notifyDataSetChanged();
                    callVolley();
                }
            });

        } catch (Exception e){
            Log.d("Test", e.getMessage());
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                detailAkun(idx);
            }
        });
    }

    private void detailAkun(String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    String idx = jobj.getString("id");
                    String usernamex = jobj.getString("username");
                    String passwordx = jobj.getString("password");
                    String namlengx = jobj.getString("nama_lengkap");
                    String lahir = jobj.getString("tanggal_lahir");
                    String gender = jobj.getString("jenis_kelamin");
                    String alamat = jobj.getString("alamat");
                    String nohp = jobj.getString("nohp");
                    String tingban = jobj.getString("tb");
                    String berban = jobj.getString("bb");

                    Intent intent = new Intent(UserActivity.this, DetailAkunActivity.class);
                    intent.putExtra("id", idx);
                    intent.putExtra("username", usernamex);
                    intent.putExtra("password", passwordx);
                    intent.putExtra("nama_lengkap", namlengx);
                    intent.putExtra("tanggal_lahir", lahir);
                    intent.putExtra("jenis_kelamin", gender);
                    intent.putExtra("alamat", alamat);
                    intent.putExtra("nohp", nohp);
                    intent.putExtra("tb", tingban);
                    intent.putExtra("bb", berban);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("id", id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }


    @Override
    public void onRefresh() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    public void callVolley(){
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        JsonArrayRequest jArr = new JsonArrayRequest(Db_Contract.URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        data item = new data();

                        //item.setId(obj.getString("id"));
                        item.setUsername(obj.getString("username"));
                        //item.setPassword(obj.getString("password"));
                        item.setNama_lengkap(obj.getString("nama_lengkap"));
//                        item.setTanggal_lahir(obj.getString("tanggal_lahir"));
//                        item.setJenis_kelamin(obj.getString("jenis_kelamin"));
//                        item.setAlamat(obj.getString("alamat"));
//                        item.setNohp(obj.getString("nohp"));
//                        item.setTb(obj.getString("tb"));
//                        item.setBb(obj.getString("bb"));

                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapter.notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error : " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);
    }
}