package com.xmu.makerspace.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SeatPK implements Serializable {
    private int roomNo;
    private int seatNo;

    @Column(name = "room_no")
    @Id
    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    @Column(name = "seat_no")
    @Id
    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeatPK seatPK = (SeatPK) o;

        if (roomNo != seatPK.roomNo) return false;
        if (seatNo != seatPK.seatNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomNo;
        result = 31 * result + seatNo;
        return result;
    }
}
