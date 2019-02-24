package com.xmu.makerspace.model;

/**
 * Created by status200 on 2017/9/8.
 */
public class SimpleSeatInfo {

    private int roomNo;

    private int seatNo;

    public SimpleSeatInfo() {
    }

    public SimpleSeatInfo(int roomNo, int seatNo) {
        this.roomNo = roomNo;
        this.seatNo = seatNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }


    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "SimpleSeatInfo{" +
                "roomNo=" + roomNo +
                ", seatNo=" + seatNo +
                '}';
    }
}
