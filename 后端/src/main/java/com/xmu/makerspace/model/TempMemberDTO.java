package com.xmu.makerspace.model;

/**
 * Created by ${Yixin} on 2017/8/5.
 */
public class TempMemberDTO {

    private String college;

    private String educationBackground;

    private String email;

    private int orderInTeam;

    private String studentId;

    private String studentName;

    private String teamId;

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TempMemberDTO() {
    }

    public TempMemberDTO(String college, String educationBackground, String email, int orderInTeam, String studentId, String studentName, String teamId, String phoneNumber) {
        this.college = college;
        this.educationBackground = educationBackground;
        this.email = email;
        this.orderInTeam = orderInTeam;
        this.studentId = studentId;
        this.studentName = studentName;
        this.teamId = teamId;
        this.phoneNumber = phoneNumber;
    }

    public String getCollege() {
        return college;
    }

    @Override
    public String toString() {
        return "TempMemberDTO{" +
                "college='" + college + '\'' +
                ", educationBackground='" + educationBackground + '\'' +
                ", email='" + email + '\'' +
                ", orderInTeam=" + orderInTeam +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrderInTeam() {
        return orderInTeam;
    }

    public void setOrderInTeam(int orderInTeam) {
        this.orderInTeam = orderInTeam;
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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
