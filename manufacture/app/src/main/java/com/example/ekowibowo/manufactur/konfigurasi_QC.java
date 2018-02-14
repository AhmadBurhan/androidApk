package com.example.ekowibowo.manufactur;

/**
 * Created by EKO WIBOWO on 8/17/2017.
 */

public class konfigurasi_QC {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.29:80/uasAPK/simpan_qc.php";
    public static final String URL_GET_ALL = "http://192.168.43.29:80/uasAPK/read_qc.php";
    public static final String URL_GET_EMP = "http://192.168.43.29:80/uasAPK/read_detail_qc.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.29:80/uasAPK/update_qc.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.29:80/uasAPK/delete_qc.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ID_PASIEN = "id_qc";
    public static final String KEY_NAMA_PASIEN = "nama_inspector";
    public static final String KEY_ALAMAT = "nama_produk"; //desg itu variabel untuk posisi
    //public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID_PASIEN = "id_qc";
    public static final String TAG_NAMA_PASIEN = "nama_inspector";
    public static final String TAG_ALAMAT = "nama_produk";
    // public static final String TAG_GAJIH = "salary";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String ID_PASIEN = "id_pasien";
    public static final String NAMA_PASIEN = "nama_pasien";
    public static final String ALAMAT = "alamat";

}
