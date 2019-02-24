package com.xmu.makerspace.domain;

import javax.persistence.*;

@Entity
@IdClass(SeatPK.class)
public class Seat {
    private int roomNo;
    private int seatNo;
    private String studentId;
    private Integer teamId;

    @Id
    @Column(name = "room_no")
    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    @Id
    @Column(name = "seat_no")
    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    @Basic
    @Column(name = "student_id")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "team_id")
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (roomNo != seat.roomNo) return false;
        if (seatNo != seat.seatNo) return false;
        if (studentId != null ? !studentId.equals(seat.studentId) : seat.studentId != null) return false;
        if (teamId != null ? !teamId.equals(seat.teamId) : seat.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomNo;
        result = 31 * result + seatNo;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}
