package com.xmu.makerspace.model.seat;

public class MaterialDTO {
    private String projectProgress;
    private String projectAchievement;
    private String plan;
    private int teamId;
    private int submintId;

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    public String getProjectAchievement() {
        return projectAchievement;
    }

    public void setProjectAchievement(String projectAchievement) {
        this.projectAchievement = projectAchievement;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSubmitId() {
        return submintId;
    }

    public void setSubmitId(int submintId) {
        this.submintId = submintId;
    }
}
