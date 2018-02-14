package com.example.ekowibowo.manufactur;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class daftar_detail_Warehouse extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextid_periksa;
    private EditText editTextnama_pasien;
    private EditText editTextjenis_penyakit;
    private EditText editTextnama_dokter;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_detail_warehouse);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi_Warehouse.ID_PASIEN);

        editTextid_periksa = (EditText) findViewById(R.id.id_periksa1);
        editTextnama_pasien = (EditText) findViewById(R.id.nama_pasien_periksa1);
        editTextjenis_penyakit = (EditText) findViewById(R.id.jenis_penyakit1);
        editTextnama_dokter = (EditText) findViewById(R.id.nama_dokter_periksa1);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextid_periksa.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_Warehouse.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                requesthandler rh = new requesthandler();
                String s = rh.sendGetRequestParam(konfigurasi_Warehouse.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi_Warehouse.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String periksa = c.getString(konfigurasi_Warehouse.TAG_PERIKSA);
            String pasien = c.getString(konfigurasi_Warehouse.TAG_NAMA_PASIEN);
            String penyakit = c.getString(konfigurasi_Warehouse.TAG_PENYAKIT);
            String dokter = c.getString(konfigurasi_Warehouse.TAG_DOKTER);

            editTextid_periksa.setText(periksa);
            editTextnama_pasien.setText(pasien);
            editTextjenis_penyakit.setText(penyakit);
            editTextnama_dokter.setText(dokter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){

        final String pasien = editTextnama_pasien.getText().toString().trim();
        final String penyakit = editTextjenis_penyakit.getText().toString().trim();
        final String dokter = editTextnama_dokter.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_Warehouse.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_Warehouse.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi_Warehouse.KEY_ID_PASIEN,id);
                hashMap.put(konfigurasi_Warehouse.KEY_NAMA_PASIEN,pasien);
                hashMap.put(konfigurasi_Warehouse.KEY_PENYAKIT,penyakit);
                hashMap.put(konfigurasi_Warehouse.KEY_DOKTER,dokter);

                requesthandler rh = new requesthandler();

                String s = rh.sendPostRequest(konfigurasi_Warehouse.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_Warehouse.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_Warehouse.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                requesthandler rh = new requesthandler();
                String s = rh.sendGetRequestParam(konfigurasi_Warehouse.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data Pemeriksaan ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(daftar_detail_Warehouse.this,daftar_Warehouse.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}
