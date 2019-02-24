package com.xmu.makerspace.model.Team;

import java.sql.Date;
import java.util.ArrayList;

/*
存放团队详细信息
 */
public class TeamMore {
    int team_id;
    String team_name;
    String blong_field;
    Date register_date;
    String project_type;
    String project_brief;//项目简介
    String project_director;//主营业务
    String work_foundation;//前期基础
    String plan;//项目计划
    ArrayList<MemberMessage> memberMessages;//团队成员列表

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public String getPlan() {
        return plan;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getBlong_field() {
        return blong_field;
    }

    public String getProject_brief() {
        return project_brief;
    }

    public String getProject_director() {
        return project_director;
    }

    public String getWork_foundation() {
        return work_foundation;
    }

    public ArrayList<MemberMessage> getMemberMessages() {
        return memberMessages;
    }

    public void setBlong_field(String blong_field) {
        this.blong_field = blong_field;
    }

    public void setProject_brief(String project_brief) {
        this.project_brief = project_brief;
    }

    public void setProject_director(String project_director) {
        this.project_director = project_director;
    }

    public void setMemberMessages(ArrayList<MemberMessage> memberMessages) {
        this.memberMessages = memberMessages;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public void setWork_foundation(String work_foundation) {
        this.work_foundation = work_foundation;
    }
}
