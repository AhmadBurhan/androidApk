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

public class daftar_detail_QC extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextid_pasien;
    private EditText editTextnama_pasien;
    private EditText editTextalamat;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id, nama, alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_detail_qc);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi_QC.ID_PASIEN);
        nama = intent.getStringExtra(konfigurasi_QC.NAMA_PASIEN);
        alamat = intent.getStringExtra(konfigurasi_QC.ALAMAT);

        editTextid_pasien = (EditText) findViewById(R.id.id_pasien1);
        editTextnama_pasien = (EditText) findViewById(R.id.nama_pasien1);
        editTextalamat = (EditText) findViewById(R.id.alamat1);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextid_pasien.setText(id);
        editTextnama_pasien.setText(nama);
        editTextalamat.setText(alamat);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_QC.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(konfigurasi_QC.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi_QC.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(konfigurasi_QC.TAG_ID_PASIEN);
            String desg = c.getString(konfigurasi_QC.TAG_NAMA_PASIEN);
            String sal = c.getString(konfigurasi_QC.TAG_ALAMAT);

            editTextid_pasien.setText(name);
            editTextnama_pasien.setText(desg);
            editTextalamat.setText(sal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee(){

        final String name = editTextnama_pasien.getText().toString().trim();
        final String desg = editTextalamat.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(daftar_detail_QC.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_QC.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi_QC.KEY_ID_PASIEN,id);
                hashMap.put(konfigurasi_QC.KEY_NAMA_PASIEN,name);
                hashMap.put(konfigurasi_QC.KEY_ALAMAT,desg);

                requesthandler rh = new requesthandler();

                String s = rh.sendPostRequest(konfigurasi_QC.URL_UPDATE_EMP,hashMap);

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
                loading = ProgressDialog.show(daftar_detail_QC.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(daftar_detail_QC.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                requesthandler rh = new requesthandler();
                String s = rh.sendGetRequestParam(konfigurasi_QC.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data Pasien ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(daftar_detail_QC.this,daftar_QC.class));
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
