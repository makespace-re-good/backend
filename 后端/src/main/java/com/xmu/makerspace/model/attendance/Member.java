package com.xmu.makerspace.model.attendance;

/**
 * Created by status200 on 2017/9/16.
 */
public class Member {

    private String studentName;

    private String studentId;

    private Integer attendanceNumber;


    public Member() {
    }

    public Member(String studentName, String studentId, int attendanceNumber) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.attendanceNumber = attendanceNumber;
    }

    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(Integer attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }
}
