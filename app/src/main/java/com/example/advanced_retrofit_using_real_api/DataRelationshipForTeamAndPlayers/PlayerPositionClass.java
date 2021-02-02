package com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers;

public class PlayerPositionClass {
    private String name;
    private int id;

    public PlayerPositionClass() {}

    public PlayerPositionClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
