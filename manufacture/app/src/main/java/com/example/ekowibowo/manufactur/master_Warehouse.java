package com.example.ekowibowo.manufactur;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class master_Warehouse extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextnama_pasien;
    private EditText editTextjenis_penyakit;
    private EditText editTextnama_dokter;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_warehouse);
        //Inisialisasi dari View
        editTextnama_pasien = (EditText) findViewById(R.id.nama_pasien_periksa);
        editTextjenis_penyakit = (EditText) findViewById(R.id.jenis_penyakit);
        editTextnama_dokter = (EditText) findViewById(R.id.nama_dokter_periksa);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nama_pasien = editTextnama_pasien.getText().toString().trim();
        final String jenis_penyakit = editTextjenis_penyakit.getText().toString().trim();
        final String nama_dokter = editTextnama_dokter.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(master_Warehouse.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(master_Warehouse.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi_Warehouse.KEY_NAMA_PASIEN,nama_pasien);
                params.put(konfigurasi_Warehouse.KEY_PENYAKIT,jenis_penyakit);
                params.put(konfigurasi_Warehouse.KEY_DOKTER,nama_dokter);

                requesthandler rh = new requesthandler();
                String res = rh.sendPostRequest(konfigurasi_Warehouse.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addEmployee();
        }

        if(v == buttonView){
            startActivity(new Intent(this,daftar_Warehouse.class));
        }
    }
}
