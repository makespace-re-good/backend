package com.xmu.makerspace.model;

import com.xmu.makerspace.model.Team.TempTeamDTO;

public class NumTempTeamDTO extends TempTeamDTO {
    String numOfMember;

    public String getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(String numOfMember) {
        this.numOfMember = numOfMember;
    }
}
