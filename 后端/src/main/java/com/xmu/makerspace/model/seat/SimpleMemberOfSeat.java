package com.xmu.makerspace.model.seat;

/**
 * Created by status200 on 2017/9/8.
 */
public class SimpleMemberOfSeat {

    private String studentId;

    private String studentName;

    private int teamId;

    private String teamName;

    public SimpleMemberOfSeat() {
    }

    public SimpleMemberOfSeat(String studentId, String studentName, int teamId, String teamName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.teamId = teamId;
        this.teamName = teamName;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }


    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "SimpleMemberOfSeat{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
