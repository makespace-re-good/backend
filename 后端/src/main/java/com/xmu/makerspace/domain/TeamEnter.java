package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by status200 on 2017/9/18.
 */
@Entity
@Table(name = "team_enter", schema = "makerspace", catalog = "")
public class TeamEnter implements Serializable {
    private String password;
    private Timestamp lastEnterTime;
    private int teamId;

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_enter_time", nullable = true)
    public Timestamp getLastEnterTime() {
        return lastEnterTime;
    }

    public void setLastEnterTime(Timestamp lastEnterTime) {
        this.lastEnterTime = lastEnterTime;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEnter teamEnter = (TeamEnter) o;

        if (teamId != teamEnter.teamId) return false;
        if (password != null ? !password.equals(teamEnter.password) : teamEnter.password != null) return false;
        if (lastEnterTime != null ? !lastEnterTime.equals(teamEnter.lastEnterTime) : teamEnter.lastEnterTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (lastEnterTime != null ? lastEnterTime.hashCode() : 0);
        result = 31 * result + teamId;
        return result;
    }
}
