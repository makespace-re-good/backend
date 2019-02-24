package com.xmu.makerspace.domain;

import javax.persistence.*;

@Entity
@Table(name = "middle_term_submit", schema = "makerspace", catalog = "")
public class MiddleTermSubmit {
    private String projectProgress;
    private String projectAchievement;
    private String plan;
    private Integer teamId;
    private int submitId;

    @Basic
    @Column(name = "project_progress")
    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

    @Basic
    @Column(name = "project_achievement")
    public String getProjectAchievement() {
        return projectAchievement;
    }

    public void setProjectAchievement(String projectAchievement) {
        this.projectAchievement = projectAchievement;
    }

    @Basic
    @Column(name = "plan")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Basic
    @Column(name = "team_id")
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Id
    @Column(name = "submit_id")
    public int getSubmitId() {
        return submitId;
    }

    public void setSubmitId(int submitId) {
        this.submitId = submitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiddleTermSubmit that = (MiddleTermSubmit) o;

        if (submitId != that.submitId) return false;
        if (projectProgress != null ? !projectProgress.equals(that.projectProgress) : that.projectProgress != null)
            return false;
        if (projectAchievement != null ? !projectAchievement.equals(that.projectAchievement) : that.projectAchievement != null)
            return false;
        if (plan != null ? !plan.equals(that.plan) : that.plan != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectProgress != null ? projectProgress.hashCode() : 0;
        result = 31 * result + (projectAchievement != null ? projectAchievement.hashCode() : 0);
        result = 31 * result + (plan != null ? plan.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + submitId;
        return result;
    }
}
