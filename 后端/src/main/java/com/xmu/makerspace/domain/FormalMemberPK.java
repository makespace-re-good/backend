package com.xmu.makerspace.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by status200 on 2017/9/1.
 */
public class FormalMemberPK implements Serializable {
    private Integer teamId;
    private Short orderInTeam;

    @Column(name = "team_id", nullable = false)
    @Id
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Column(name = "order_in_team", nullable = false)
    @Id
    public Short getOrderInTeam() {
        return orderInTeam;
    }

    public void setOrderInTeam(Short orderInTeam) {
        this.orderInTeam = orderInTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormalMemberPK that = (FormalMemberPK) o;

        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (orderInTeam != null ? !orderInTeam.equals(that.orderInTeam) : that.orderInTeam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId != null ? teamId.hashCode() : 0;
        result = 31 * result + (orderInTeam != null ? orderInTeam.hashCode() : 0);
        return result;
    }
}
