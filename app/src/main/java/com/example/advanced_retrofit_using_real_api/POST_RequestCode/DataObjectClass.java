package com.example.advanced_retrofit_using_real_api.POST_RequestCode;

public class DataObjectClass {
    private String name;
    private String id;
    private String email;
    private String gender;
    private LastLoginObject last_login;

    public DataObjectClass() {}

    public DataObjectClass(String name, String id, String email, String gender, LastLoginObject last_login) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.gender = gender;
        this.last_login = last_login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LastLoginObject getLast_login() {
        return last_login;
    }

    public void setLast_login(LastLoginObject last_login) {
        this.last_login = last_login;
    }
}
