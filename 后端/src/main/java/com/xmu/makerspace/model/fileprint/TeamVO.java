package com.xmu.makerspace.model.fileprint;

import com.xmu.makerspace.model.SimpleSeatInfo;

import java.util.List;

/**
 * Created by status200 on 2017/9/23.
 */
public class TeamVO {

    private String teamName;

    private int teamId;

    private List<SimpleSeatInfo> seats;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public List<SimpleSeatInfo> getSeats() {
        return seats;
    }

    public void setSeats(List<SimpleSeatInfo> seats) {
        this.seats = seats;
    }
}
