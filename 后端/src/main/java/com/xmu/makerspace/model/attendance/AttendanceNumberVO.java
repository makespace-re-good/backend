package com.xmu.makerspace.model.attendance;

import java.util.List;

/**
 * Created by status200 on 2017/9/16.
 */
public class AttendanceNumberVO {

    private int teamId;

    private String teamName;

    private List<Member> members;

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
