package com.xmu.makerspace.model.Team;
/*
 用来存放队伍列表的信息
 */
import com.xmu.makerspace.domain.FormalTeam;

import java.util.ArrayList;

public class TeamListVO {
    ArrayList<TeamMessage> formalTeams;
    int part;
    int all;

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public ArrayList<TeamMessage> getFormalTeams() {
        return formalTeams;
    }

    public void setFormalTeams(ArrayList<TeamMessage> formalTeams) {
        this.formalTeams = formalTeams;
    }
}
