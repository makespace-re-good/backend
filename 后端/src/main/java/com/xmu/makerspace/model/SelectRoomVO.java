package com.xmu.makerspace.model;

/**
 * Created by status200 on 2017/9/3.
 */
public class SelectRoomVO {

    private int roomNo;

    private int seatTotal;

    private int seatUsed;

    public int getRoomNo() {
        return roomNo;
    }

    @Override
    public String toString() {
        return "SelectRoomVO{" +
                "roomNo=" + roomNo +
                ", seatTotal=" + seatTotal +
                ", seatUsed=" + seatUsed +
                '}';
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getSeatTotal() {
        return seatTotal;
    }

    public void setSeatTotal(int seatTotal) {
        this.seatTotal = seatTotal;
    }

    public int getSeatUsed() {
        return seatUsed;
    }

    public void setSeatUsed(int seatUsed) {
        this.seatUsed = seatUsed;
    }
}
