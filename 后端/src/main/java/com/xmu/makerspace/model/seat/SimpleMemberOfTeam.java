package com.xmu.makerspace.model.seat;

import com.xmu.makerspace.model.SimpleSeatInfo;

/**
 * Created by status200 on 2017/9/8.
 */
public class SimpleMemberOfTeam {

    private int belongTeamNumber;

    private String studentId;

    private String studentName;

    private SimpleSeatInfo seat;

    public int getBelongTeamNumber() {
        return belongTeamNumber;
    }

    public void setBelongTeamNumber(int belongTeamNumber) {
        this.belongTeamNumber = belongTeamNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public SimpleSeatInfo getSeat() {
        return seat;
    }

    public void setSeat(SimpleSeatInfo seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "SimpleMemberOfTeam{" +
                "belongTeamNumber=" + belongTeamNumber +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", seat=" + seat +
                '}';
    }
}
