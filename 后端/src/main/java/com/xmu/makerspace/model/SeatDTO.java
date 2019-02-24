package com.xmu.makerspace.model;

public class SeatDTO {
    String seatno;
    String ok;
    String teamname;
    String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getOk() {
        return ok;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
}
