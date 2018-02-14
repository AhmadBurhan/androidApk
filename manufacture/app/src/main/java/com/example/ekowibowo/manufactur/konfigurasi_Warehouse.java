package com.example.ekowibowo.manufactur;

/**
 * Created by EKO WIBOWO on 8/18/2017.
 */

public class konfigurasi_Warehouse {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.29:80/uasAPK/simpan_warehouse.php";
    public static final String URL_GET_ALL = "http://192.168.43.29:80/uasAPK/read_warehouse.php";
    public static final String URL_GET_EMP = "http://192.168.43.29:80/uasAPK/read_detail_warehouse.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.29:80/uasAPK/update_warehouse.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.29:80/uasAPK/delete_warehouse.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ID_PASIEN = "id_warehouse";
    public static final String KEY_NAMA_PASIEN = "nama_karyawan";
    public static final String KEY_PENYAKIT = "nama_produk"; //desg itu variabel untuk posisi
    public static final String KEY_DOKTER = "jumlah_produk"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_PERIKSA = "id_warehouse";
    public static final String TAG_NAMA_PASIEN = "nama_karyawan";
    public static final String TAG_PENYAKIT = "nama_produk";
     public static final String TAG_DOKTER = "jumlah_produk";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String ID_PASIEN = "id_pemeriksaan";
    public static final String NAMA_PASIEN = "nama_pasien";
    public static final String ALAMAT = "jenis_penyakit";

}
