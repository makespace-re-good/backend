package com.xmu.makerspace.model.Team;

import com.xmu.makerspace.model.NumTempTeamDTO;

import java.util.ArrayList;

public class TempTeamList {
    ArrayList<NumTempTeamDTO> tempTeams;
    int part;
    int all;

    public int getAll() {
        return all;
    }
    public void setAll(int all) {
        this.all = all;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getPart() {
        return part;
    }

    public ArrayList<NumTempTeamDTO> getTempTeams() {
        return tempTeams;
    }

    public void setTempTeams(ArrayList<NumTempTeamDTO> tempTeams) {
        this.tempTeams = tempTeams;
    }
}
