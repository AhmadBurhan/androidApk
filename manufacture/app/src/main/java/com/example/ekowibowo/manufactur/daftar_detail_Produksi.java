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

public class daftar_detail_Produksi extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextid_dokter;
    private EditText editTextnama_dokter;
    private EditText editTextspesialis;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id, nama, alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_detail_produksi);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi_Produksi.ID_PASIEN);
        nama = intent.getStringExtra(konfigurasi_Produksi.NAMA_PASIEN);
        alamat = intent.getStringExtra(konfigurasi_Produksi.ALAMAT);

        editTextid_dokter = (EditText) findViewById(R.id.id_dokter1);
        editTextnama_dokter = (EditText) findViewById(R.id.nama_dokter1);
        editTextspesialis = (EditText) findViewById(R.id.spesialis1);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextid_dokter.setText(id);
        editTextnama_dokter.setText(nama);
        editTextspesialis.setText(alamat);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_Produksi.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(konfigurasi_Produksi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi_Produksi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(konfigurasi_Produksi.TAG_ID_PASIEN);
            String desg = c.getString(konfigurasi_Produksi.TAG_NAMA_PASIEN);
            String sal = c.getString(konfigurasi_Produksi.TAG_ALAMAT);

            editTextid_dokter.setText(name);
            editTextnama_dokter.setText(desg);
            editTextspesialis.setText(sal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){

        final String name = editTextnama_dokter.getText().toString().trim();
        final String desg = editTextspesialis.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_Produksi.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_Produksi.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi_Produksi.KEY_ID_PASIEN,id);
                hashMap.put(konfigurasi_Produksi.KEY_NAMA_PASIEN,name);
                hashMap.put(konfigurasi_Produksi.KEY_ALAMAT,desg);

                requesthandler rh = new requesthandler();

                String s = rh.sendPostRequest(konfigurasi_Produksi.URL_UPDATE_EMP,hashMap);

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
                loading = ProgressDialog.show(daftar_detail_Produksi.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_Produksi.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                requesthandler rh = new requesthandler();
                String s = rh.sendGetRequestParam(konfigurasi_Produksi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data Dokter ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(daftar_detail_Produksi.this,daftar_Produksi.class));
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

