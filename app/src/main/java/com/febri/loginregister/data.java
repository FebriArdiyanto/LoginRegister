package com.febri.loginregister;

public class data {
    private String id, username, nama_lengkap;

    public data() {
    }

    public data(String id, String username, String password, String nama_lengkap, String tanggal_lahir, String jenis_kelamin, String alamat, String nohp, String tb, String bb) {
        this.id = id;
        this.username = username;
        //this.password = password;
        this.nama_lengkap = nama_lengkap;
//        this.tanggal_lahir = tanggal_lahir;
//        this.jenis_kelamin = jenis_kelamin;
//        this.alamat = alamat;
//        this.nohp = nohp;
//        this.tb = tb;
//        this.bb = bb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

//    public String getTanggal_lahir() {
//        return tanggal_lahir;
//    }
//
//    public void setTanggal_lahir(String tanggal_lahir) {
//        this.tanggal_lahir = tanggal_lahir;
//    }
//
//    public String getJenis_kelamin() {
//        return jenis_kelamin;
//    }
//
//    public void setJenis_kelamin(String jenis_kelamin) {
//        this.jenis_kelamin = jenis_kelamin;
//    }
//
//    public String getAlamat() {
//        return alamat;
//    }
//
//    public void setAlamat(String alamat) {
//        this.alamat = alamat;
//    }
//
//    public String getNohp() {
//        return nohp;
//    }
//
//    public void setNohp(String nohp) {
//        this.nohp = nohp;
//    }
//
//    public String getTb() {
//        return tb;
//    }
//
//    public void setTb(String tb) {
//        this.tb = tb;
//    }
//
//    public String getBb() {
//        return bb;
//    }
//
//    public void setBb(String bb) {
//        this.bb = bb;
//    }
}
