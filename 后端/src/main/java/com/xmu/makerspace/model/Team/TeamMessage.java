package com.xmu.makerspace.model.Team;
/*
用来存放团队列表应该展示的数据
 */
import java.sql.Date;

public class TeamMessage {
    int teamId;
    String team_name;
    String project_name;
    String belong_field;
    String project_type;
    Date register_date;
    int message;

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public String getBelong_field() {
        return belong_field;
    }

    public String getProject_name() {
        return project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setBelong_field(String belong_field) {
        this.belong_field = belong_field;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}
