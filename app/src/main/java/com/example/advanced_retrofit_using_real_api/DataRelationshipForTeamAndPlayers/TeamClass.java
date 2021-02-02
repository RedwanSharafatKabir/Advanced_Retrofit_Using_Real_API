package com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers;

public class TeamClass {
    private String countryName;
    private int country_Id;

    public TeamClass() {}

    public TeamClass(String countryName, int country_Id) {
        this.countryName = countryName;
        this.country_Id = country_Id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountry_Id() {
        return country_Id;
    }

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }
}
