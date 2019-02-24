package com.xmu.makerspace.domain;

import javax.persistence.*;

@Entity
@Table(name = "mes_num", schema = "makerspace", catalog = "")
public class MesNum {
    private int teamId;
    private int messageNum;

    @Id
    @Column(name = "team_id")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "message_num")
    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MesNum mesNum = (MesNum) o;

        if (teamId != mesNum.teamId) return false;
        if (messageNum != mesNum.messageNum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId;
        result = 31 * result + messageNum;
        return result;
    }
}
