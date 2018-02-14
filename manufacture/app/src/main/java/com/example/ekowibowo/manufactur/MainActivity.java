package com.example.ekowibowo.manufactur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listview = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.manufacture));


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent myintent = new Intent(view.getContext(), master_QC.class);
                    startActivityForResult(myintent, 0);
                }
                if (i == 1) {
                    Intent myintent = new Intent(view.getContext(), master_Produksi.class);
                    startActivityForResult(myintent, 1);
                }
                if (i == 2) {
                    Intent myintent = new Intent(view.getContext(), master_Karyawan.class);
                    startActivityForResult(myintent, 2);
                }
                if (i == 3) {
                    Intent myintent = new Intent(view.getContext(), master_Warehouse.class);
                    startActivityForResult(myintent, 3);
                }


            }
        });

        listview.setAdapter(mAdapter);
    }

}


