package com.febri.loginregister;

import java.util.List;

public class akunResponse {

    private int code;
    private String status;
    private List<akun> user_list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<akun> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<akun> user_list) {
        this.user_list = user_list;
    }
}
