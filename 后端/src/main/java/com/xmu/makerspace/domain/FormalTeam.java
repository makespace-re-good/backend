package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "formal_team", schema = "makerspace", catalog = "")
public class FormalTeam {
    private int teamId;
    private String teamName;
    private String projectName;
    private String belongField;
    private String projectType;
    private String projectBrief;
    private String projectDerector;
    private String workFoundation;
    private String plan;
    private Date registerDate;
    private Integer auditStep;

    @Id
    @Column(name = "TEAM_ID")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "TEAM_NAME")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "PROJECT_NAME")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "BELONG_FIELD")
    public String getBelongField() {
        return belongField;
    }

    public void setBelongField(String belongField) {
        this.belongField = belongField;
    }

    @Basic
    @Column(name = "PROJECT_TYPE")
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Basic
    @Column(name = "PROJECT_BRIEF")
    public String getProjectBrief() {
        return projectBrief;
    }

    public void setProjectBrief(String projectBrief) {
        this.projectBrief = projectBrief;
    }

    @Basic
    @Column(name = "PROJECT_DERECTOR")
    public String getProjectDerector() {
        return projectDerector;
    }

    public void setProjectDerector(String projectDerector) {
        this.projectDerector = projectDerector;
    }

    @Basic
    @Column(name = "WORK_FOUNDATION")
    public String getWorkFoundation() {
        return workFoundation;
    }

    public void setWorkFoundation(String workFoundation) {
        this.workFoundation = workFoundation;
    }

    @Basic
    @Column(name = "PLAN")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Basic
    @Column(name = "REGISTER_DATE")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "AUDIT_STEP")
    public Integer getAuditStep() {
        return auditStep;
    }

    public void setAuditStep(Integer auditStep) {
        this.auditStep = auditStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormalTeam that = (FormalTeam) o;

        if (teamId != that.teamId) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (belongField != null ? !belongField.equals(that.belongField) : that.belongField != null) return false;
        if (projectType != null ? !projectType.equals(that.projectType) : that.projectType != null) return false;
        if (projectBrief != null ? !projectBrief.equals(that.projectBrief) : that.projectBrief != null) return false;
        if (projectDerector != null ? !projectDerector.equals(that.projectDerector) : that.projectDerector != null)
            return false;
        if (workFoundation != null ? !workFoundation.equals(that.workFoundation) : that.workFoundation != null)
            return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null) return false;
        if (registerDate != null ? !registerDate.equals(that.registerDate) : that.registerDate != null) return false;
        if (auditStep != null ? !auditStep.equals(that.auditStep) : that.auditStep != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (belongField != null ? belongField.hashCode() : 0);
        result = 31 * result + (projectType != null ? projectType.hashCode() : 0);
        result = 31 * result + (projectBrief != null ? projectBrief.hashCode() : 0);
        result = 31 * result + (projectDerector != null ? projectDerector.hashCode() : 0);
        result = 31 * result + (workFoundation != null ? workFoundation.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        result = 31 * result + (auditStep != null ? auditStep.hashCode() : 0);
        return result;
    }
}
