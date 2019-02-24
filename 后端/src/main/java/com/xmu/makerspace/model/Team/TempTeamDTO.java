package com.xmu.makerspace.model.Team;

import java.sql.Date;

public class TempTeamDTO {
    String team_id;
    String team_name;
    String captain;
    String captain_phone;
    String audit_option;
    Date submit_date;

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public String getAudit_option() {
        return audit_option;
    }

    public String getCaptain() {
        return captain;
    }

    public String getCaptain_phone() {
        return captain_phone;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setAudit_option(String audit_option) {
        this.audit_option = audit_option;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setCaptain_phone(String captain_phone) {
        this.captain_phone = captain_phone;
    }

    public void setSubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }
}
