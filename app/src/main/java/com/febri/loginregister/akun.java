package com.febri.loginregister;

public class akun {

    private int id;
    private String username;
    private String password;
    private String nama_lengkap;
    private String alamat;
    private String nohp;

    public akun() {
    }

    public akun(int id, String username, String password, String nama_lengkap, String alamat, String nohp) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama_lengkap = nama_lengkap;
        this.alamat = alamat;
        this.nohp = nohp;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
