package com.example.advanced_retrofit_using_real_api.Upload_Image_with_Retrofit;

public class ResponseImageClass {
    private String message;

    public ResponseImageClass() {}

    public ResponseImageClass(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
