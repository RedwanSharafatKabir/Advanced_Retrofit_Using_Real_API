package com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers;

public class PlayerObjectDesignClass {
    private String fullname;
    private String dateofbirth;
    private String gender;
    private PlayerPositionClass position;

    public PlayerObjectDesignClass() {}

    public PlayerObjectDesignClass(String fullname, String dateofbirth, String gender, PlayerPositionClass position) {
        this.fullname = fullname;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
        this.position = position;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PlayerPositionClass getPosition() {
        return position;
    }

    public void setPosition(PlayerPositionClass position) {
        this.position = position;
    }
}
