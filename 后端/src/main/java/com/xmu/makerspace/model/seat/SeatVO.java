package com.xmu.makerspace.model.seat;

import java.util.List;

/**
 * Created by status200 on 2017/9/8.
 */
public class SeatVO {

    private int roomNo;

    private int seatNo;

    private List<SimpleMemberOfSeat> member;

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

    public List<SimpleMemberOfSeat> getMember() {
        return member;
    }

    public void setMember(List<SimpleMemberOfSeat> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "SeatVO{" +
                "roomNo=" + roomNo +
                ", seatNo=" + seatNo +
                ", member=" + member +
                '}';
    }
}
