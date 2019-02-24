package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "application_round", schema = "makerspace", catalog = "")
public class ApplicationRound {
    private int termId;
    private Short teamNumber;
    private Date beginDate;
    private Date endDate;

    @Id
    @Column(name = "term_id")
    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    @Basic
    @Column(name = "team_number")
    public Short getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Short teamNumber) {
        this.teamNumber = teamNumber;
    }

    @Basic
    @Column(name = "begin_date")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationRound that = (ApplicationRound) o;

        if (termId != that.termId) return false;
        if (teamNumber != null ? !teamNumber.equals(that.teamNumber) : that.teamNumber != null) return false;
        if (beginDate != null ? !beginDate.equals(that.beginDate) : that.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = termId;
        result = 31 * result + (teamNumber != null ? teamNumber.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }
}
