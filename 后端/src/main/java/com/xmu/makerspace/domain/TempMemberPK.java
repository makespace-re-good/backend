package com.xmu.makerspace.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by status200 on 2017/8/9.
 */
public class TempMemberPK implements Serializable {
    private String teamId;
    private short orderInTeam;

    @Column(name = "team_id", nullable = false, length = 80)
    @Id
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Column(name = "order_in_team", nullable = false)
    @Id
    public short getOrderInTeam() {
        return orderInTeam;
    }

    public void setOrderInTeam(short orderInTeam) {
        this.orderInTeam = orderInTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempMemberPK that = (TempMemberPK) o;

        if (orderInTeam != that.orderInTeam) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId != null ? teamId.hashCode() : 0;
        result = 31 * result + (int) orderInTeam;
        return result;
    }
}
