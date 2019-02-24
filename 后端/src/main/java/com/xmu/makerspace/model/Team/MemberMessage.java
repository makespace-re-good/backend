package com.xmu.makerspace.model.Team;

/*
用来存放学生列表的单条数据
 */
public class MemberMessage {
    String student_name;
    String student_id;
    int attendance_number;//考勤号
    int room_no;//房间号
    int seat_no;//座位号

    public int getAttendance_number() {
        return attendance_number;
    }

    public int getRoom_no() {
        return room_no;
    }

    public int getSeat_no() {
        return seat_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setAttendance_number(int attendance_number) {
        this.attendance_number = attendance_number;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
