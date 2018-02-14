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

public class master_Karyawan extends AppCompatActivity implements View.OnClickListener  {
    private EditText editTextnama_obat;
    private EditText editTextjenis_obat;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_karyawan);
        //Inisialisasi dari View
        editTextnama_obat = (EditText) findViewById(R.id.nama_obat);
        editTextjenis_obat = (EditText) findViewById(R.id.jenis_obat);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nama_obat = editTextnama_obat.getText().toString().trim();
        final String jenis_obat = editTextjenis_obat.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(master_Karyawan.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(master_Karyawan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi_Karyawan.KEY_NAMA_PASIEN,nama_obat);
                params.put(konfigurasi_Karyawan.KEY_ALAMAT,jenis_obat);

                requesthandler rh = new requesthandler();
                String res = rh.sendPostRequest(konfigurasi_Karyawan.URL_ADD, params);
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
            startActivity(new Intent(this,daftar_Karyawan.class));
        }
    }
}
