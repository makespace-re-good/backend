package com.xmu.makerspace.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by status200 on 2017/9/1.
 */
@Entity
public class Student {
    private String studentId;
    private Integer attendanceNumber;

    @Id
    @Column(name = "student_id", nullable = false, length = 20)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "attendance_number", nullable = true)
    public Integer getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(Integer attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentId != null ? !studentId.equals(student.studentId) : student.studentId != null) return false;
        if (attendanceNumber != null ? !attendanceNumber.equals(student.attendanceNumber) : student.attendanceNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (attendanceNumber != null ? attendanceNumber.hashCode() : 0);
        return result;
    }
}
