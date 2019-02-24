package com.xmu.makerspace.model;

import java.util.Date;

/**
 * Created by status200 on 2017/8/23.
 */
public class ManageRegisterVO extends UpdateRegisterVO{
    private String auditOpinion;

    private Date submitDate;

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }
}
