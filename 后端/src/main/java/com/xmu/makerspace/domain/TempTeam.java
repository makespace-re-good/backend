package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by status200 on 2017/8/9.
 */
@Entity
@Table(name = "temp_team", schema = "makerspace")
public class TempTeam {
    private String teamId;
    private String teamName;
    private String projectName;
    private String belongField;
    private String projectType;
    private String projectBrief;
    private String projectDirector;
    private String workFoundation;
    private String plan;
    private String auditOpinion;
    private Date submitDate;
    private Date enterDueDate;

    @Id
    @Column(name = "team_id", nullable = false, length = 80)
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "team_name", nullable = true, length = 30)
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "project_name", nullable = true, length = 255)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "belong_field", nullable = true, length = 30)
    public String getBelongField() {
        return belongField;
    }

    public void setBelongField(String belongField) {
        this.belongField = belongField;
    }

    @Basic
    @Column(name = "project_type", nullable = true, length = 30)
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Basic
    @Column(name = "project_brief", nullable = true, length = -1)
    public String getProjectBrief() {
        return projectBrief;
    }

    public void setProjectBrief(String projectBrief) {
        this.projectBrief = projectBrief;
    }

    @Basic
    @Column(name = "project_director", nullable = true, length = -1)
    public String getProjectDirector() {
        return projectDirector;
    }

    public void setProjectDirector(String projectDirector) {
        this.projectDirector = projectDirector;
    }

    @Basic
    @Column(name = "work_foundation", nullable = true, length = -1)
    public String getWorkFoundation() {
        return workFoundation;
    }

    public void setWorkFoundation(String workFoundation) {
        this.workFoundation = workFoundation;
    }

    @Basic
    @Column(name = "plan", nullable = true, length = -1)
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Basic
    @Column(name = "audit_opinion", nullable = true, length = 15)
    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    @Basic
    @Column(name = "submit_date", nullable = true)
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Basic
    @Column(name = "enter_due_date", nullable = true)
    public Date getEnterDueDate() {
        return enterDueDate;
    }

    public void setEnterDueDate(Date enterDueDate) {
        this.enterDueDate = enterDueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempTeam tempTeam = (TempTeam) o;

        if (teamId != null ? !teamId.equals(tempTeam.teamId) : tempTeam.teamId != null) return false;
        if (teamName != null ? !teamName.equals(tempTeam.teamName) : tempTeam.teamName != null) return false;
        if (projectName != null ? !projectName.equals(tempTeam.projectName) : tempTeam.projectName != null)
            return false;
        if (belongField != null ? !belongField.equals(tempTeam.belongField) : tempTeam.belongField != null)
            return false;
        if (projectType != null ? !projectType.equals(tempTeam.projectType) : tempTeam.projectType != null)
            return false;
        if (projectBrief != null ? !projectBrief.equals(tempTeam.projectBrief) : tempTeam.projectBrief != null)
            return false;
        if (projectDirector != null ? !projectDirector.equals(tempTeam.projectDirector) : tempTeam.projectDirector != null)
            return false;
        if (workFoundation != null ? !workFoundation.equals(tempTeam.workFoundation) : tempTeam.workFoundation != null)
            return false;
        if (plan != null ? !plan.equals(tempTeam.plan) : tempTeam.plan != null) return false;
        if (auditOpinion != null ? !auditOpinion.equals(tempTeam.auditOpinion) : tempTeam.auditOpinion != null)
            return false;
        if (submitDate != null ? !submitDate.equals(tempTeam.submitDate) : tempTeam.submitDate != null) return false;
        if (enterDueDate != null ? !enterDueDate.equals(tempTeam.enterDueDate) : tempTeam.enterDueDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId != null ? teamId.hashCode() : 0;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (belongField != null ? belongField.hashCode() : 0);
        result = 31 * result + (projectType != null ? projectType.hashCode() : 0);
        result = 31 * result + (projectBrief != null ? projectBrief.hashCode() : 0);
        result = 31 * result + (projectDirector != null ? projectDirector.hashCode() : 0);
        result = 31 * result + (workFoundation != null ? workFoundation.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (auditOpinion != null ? auditOpinion.hashCode() : 0);
        result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
        result = 31 * result + (enterDueDate != null ? enterDueDate.hashCode() : 0);
        return result;
    }
}
