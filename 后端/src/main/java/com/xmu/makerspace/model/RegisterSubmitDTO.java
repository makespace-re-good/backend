package com.xmu.makerspace.model;

import java.util.List;

/**
 * Created by ${Yixin} on 2017/8/5.
 */
public class    RegisterSubmitDTO {

    private String belongField;

    private String plan;

    private String projectBrief;

    private String projectDirector;

    private String projectName;

    private String projectType;

    private String teamId;

    private String teamName;

    private String workFoundation;

    private List<TempMemberDTO> members;

    public String getBelongField() {
        return belongField;
    }

    public void setBelongField(String belongField) {
        this.belongField = belongField;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getProjectBrief() {
        return projectBrief;
    }

    public void setProjectBrief(String projectBrief) {
        this.projectBrief = projectBrief;
    }

    public String getProjectDirector() {
        return projectDirector;
    }

    public void setProjectDirector(String projectDirector) {
        this.projectDirector = projectDirector;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getWorkFoundation() {
        return workFoundation;
    }

    public void setWorkFoundation(String workFoundation) {
        this.workFoundation = workFoundation;
    }

    public List<TempMemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<TempMemberDTO> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "RegisterSubmitDTO{" +
                "belongField='" + belongField + '\'' +
                ", plan='" + plan + '\'' +
                ", projectBrief='" + projectBrief + '\'' +
                ", projectDirector='" + projectDirector + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectType='" + projectType + '\'' +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", workFoundation='" + workFoundation + '\'' +
                ", members=" + members +
                '}';
    }
}
