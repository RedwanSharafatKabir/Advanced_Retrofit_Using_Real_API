package com.example.advanced_retrofit_using_real_api.GET_RequestCode;

import java.util.List;

public class ContinentListDataClass {
    private List<ContinentDataClass> data;

    public ContinentListDataClass() {}

    public ContinentListDataClass(List<ContinentDataClass> data) {
        this.data = data;
    }

    public List<ContinentDataClass> getData() {
        return data;
    }

    public void setData(List<ContinentDataClass> data) {
        this.data = data;
    }
}
