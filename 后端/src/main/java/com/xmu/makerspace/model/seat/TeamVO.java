package com.xmu.makerspace.model.seat;

import java.util.List;

/**
 * Created by status200 on 2017/9/8.
 */
public class TeamVO {

    private int teamId;

    private String teamName;

    private List<SimpleMemberOfTeam> members;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<SimpleMemberOfTeam> getMembers() {
        return members;
    }

    public void setMembers(List<SimpleMemberOfTeam> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "TeamVO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", members=" + members +
                '}';
    }
}
