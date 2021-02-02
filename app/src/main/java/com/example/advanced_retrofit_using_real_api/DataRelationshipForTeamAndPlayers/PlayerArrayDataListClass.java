package com.example.advanced_retrofit_using_real_api.DataRelationshipForTeamAndPlayers;

import java.util.List;

public class PlayerArrayDataListClass {
    private List<PlayerObjectDesignClass> data;

    public PlayerArrayDataListClass() {}

    public PlayerArrayDataListClass(List<PlayerObjectDesignClass> data) {
        this.data = data;
    }

    public List<PlayerObjectDesignClass> getData() {
        return data;
    }

    public void setData(List<PlayerObjectDesignClass> data) {
        this.data = data;
    }
}
