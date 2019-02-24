package com.xmu.makerspace.domain;

import javax.persistence.*;

@Entity
@Table(name = "middle_term_result", schema = "makerspace", catalog = "")
public class MiddleTermResult {
    private int teamId;
    private Integer grade;
    private String reviewOpinion;

    @Id
    @Column(name = "team_id")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "grade")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "review_opinion")
    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiddleTermResult that = (MiddleTermResult) o;

        if (teamId != that.teamId) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (reviewOpinion != null ? !reviewOpinion.equals(that.reviewOpinion) : that.reviewOpinion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (reviewOpinion != null ? reviewOpinion.hashCode() : 0);
        return result;
    }
}
