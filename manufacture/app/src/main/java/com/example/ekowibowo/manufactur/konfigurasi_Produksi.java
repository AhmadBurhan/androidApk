package com.example.ekowibowo.manufactur;

/**
 * Created by EKO WIBOWO on 8/17/2017.
 */

public class konfigurasi_Produksi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.29:80/uasAPK/simpan_produksi.php";
    public static final String URL_GET_ALL = "http://192.168.43.29:80/uasAPK/read_produksi.php";
    public static final String URL_GET_EMP = "http://192.168.43.29:80/uasAPK/read_detail_produksi.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.29:80/uasAPK/update_produksi.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.29:80/uasAPK/delete_produksi.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ID_PASIEN = "id_operator";
    public static final String KEY_NAMA_PASIEN = "nama_operator";
    public static final String KEY_ALAMAT = "mesin"; //desg itu variabel untuk posisi
    //public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID_PASIEN = "id_operator";
    public static final String TAG_NAMA_PASIEN = "nama_operator";
    public static final String TAG_ALAMAT = "mesin";
    // public static final String TAG_GAJIH = "salary";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String ID_PASIEN = "id_operator";
    public static final String NAMA_PASIEN = "nama_operator";
    public static final String ALAMAT = "mesin";

}
