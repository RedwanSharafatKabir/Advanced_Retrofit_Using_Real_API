package com.example.advanced_retrofit_using_real_api.PUT_PATCH_DELETE_RequestCode;

public class ObjectStructure {
    private String title;
    private String body;
    private int userId;

    public ObjectStructure() {}

    public ObjectStructure(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
