package com.example.advanced_retrofit_using_real_api.POST_RequestCode;

public class MainObjectClass {
    private String token;
    private DataObjectClass data;

    public MainObjectClass() {}

    public MainObjectClass(String token, DataObjectClass data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataObjectClass getData() {
        return data;
    }

    public void setData(DataObjectClass data) {
        this.data = data;
    }
}
