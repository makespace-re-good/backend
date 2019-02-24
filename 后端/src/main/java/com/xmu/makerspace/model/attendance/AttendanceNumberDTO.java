package com.xmu.makerspace.model.attendance;

/**
 * Created by status200 on 2017/9/16.
 */
public class AttendanceNumberDTO {
    private String studentId;

    private int attendanceNumber;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(int attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }
}
