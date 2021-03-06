package com.xmu.makerspace.domain;

import javax.persistence.*;

/**
 * Created by status200 on 2017/8/9.
 */
@Entity
@Table(name = "temp_member", schema = "makerspace")
@IdClass(TempMemberPK.class)
public class TempMember {
    private String teamId;
    private short orderInTeam;
    private String studentId;
    private String studentName;
    private String college;
    private String educationBackground;
    private String phoneNumber;
    private String email;

    @Id
    @Column(name = "team_id", nullable = false, length = 80)
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Id
    @Column(name = "order_in_team", nullable = false)
    public short getOrderInTeam() {
        return orderInTeam;
    }

    public void setOrderInTeam(short orderInTeam) {
        this.orderInTeam = orderInTeam;
    }

    @Basic
    @Column(name = "student_id", nullable = true, length = 20)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "student_name", nullable = true, length = 20)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "college", nullable = true, length = 30)
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Basic
    @Column(name = "education_background", nullable = true, length = 30)
    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempMember that = (TempMember) o;

        if (orderInTeam != that.orderInTeam) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (college != null ? !college.equals(that.college) : that.college != null) return false;
        if (educationBackground != null ? !educationBackground.equals(that.educationBackground) : that.educationBackground != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamId != null ? teamId.hashCode() : 0;
        result = 31 * result + (int) orderInTeam;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        result = 31 * result + (educationBackground != null ? educationBackground.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
