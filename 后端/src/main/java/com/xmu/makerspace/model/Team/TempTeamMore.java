package com.xmu.makerspace.model.Team;

import com.xmu.makerspace.domain.TempMember;
import com.xmu.makerspace.domain.TempTeam;

import java.util.ArrayList;

public class TempTeamMore extends TempTeam{
    ArrayList<TempMember> tempMembers;
    String filePath;
    String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<TempMember> getTempMembers() {
        return tempMembers;
    }

    public void setTempMembers(ArrayList<TempMember> tempMembers) {
        this.tempMembers = tempMembers;
    }
}
