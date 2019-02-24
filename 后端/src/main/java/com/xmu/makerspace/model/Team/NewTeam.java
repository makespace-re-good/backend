package com.xmu.makerspace.model.Team;

import java.util.ArrayList;

/**
 * 团队申请的类
 */
public class NewTeam {
    String team_name;
    String project_name;
    String belong_field;
    String project_type;
    String project_brief;
    String project_derector;
    String plan;
    String work_foundation;
    ArrayList<NewStudent> newStudents;

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public void setBelong_field(String belong_field) {
        this.belong_field = belong_field;
    }

    public String getBelong_field() {
        return belong_field;
    }

    public ArrayList<NewStudent> getNewStudents() {
        return newStudents;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setNewStudents(ArrayList<NewStudent> newStudents) {
        this.newStudents = newStudents;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setWork_foundation(String work_foundation) {
        this.work_foundation = work_foundation;
    }

    public void setProject_brief(String project_brief) {
        this.project_brief = project_brief;
    }

    public String getWork_foundation() {
        return work_foundation;
    }

    public String getProject_brief() {
        return project_brief;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

    public String getProject_derector() {
        return project_derector;
    }

    public void setProject_derector(String project_derector) {
        this.project_derector = project_derector;
    }
}
