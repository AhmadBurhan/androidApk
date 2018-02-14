package com.example.ekowibowo.manufactur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class daftar_Warehouse extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_warehouse);
        listView = (ListView) findViewById(R.id.listView_periksa);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi_Warehouse.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String periksa = jo.getString(konfigurasi_Warehouse.TAG_PERIKSA);
                String pasien = jo.getString(konfigurasi_Warehouse.TAG_NAMA_PASIEN);
                String penyakit = jo.getString(konfigurasi_Warehouse.TAG_PENYAKIT);
                String dokter = jo.getString(konfigurasi_Warehouse.TAG_DOKTER);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi_Warehouse.TAG_PERIKSA,periksa);
                employees.put(konfigurasi_Warehouse.TAG_NAMA_PASIEN,pasien);
                employees.put(konfigurasi_Warehouse.TAG_PENYAKIT,penyakit);
                employees.put(konfigurasi_Warehouse.TAG_DOKTER,dokter);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                daftar_Warehouse.this, list, R.layout.list_view,
                new String[]{konfigurasi_Warehouse.TAG_PERIKSA, konfigurasi_Warehouse.TAG_NAMA_PASIEN},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_Warehouse.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                requesthandler rh = new requesthandler();
                String s = rh.sendGetRequest(konfigurasi_Warehouse.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, daftar_detail_Warehouse.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi_Warehouse.TAG_PERIKSA).toString();
        intent.putExtra(konfigurasi_Warehouse.ID_PASIEN,empId);
        startActivity(intent);
    }
}
