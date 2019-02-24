package com.xmu.makerspace.domain;

import javax.persistence.*;

/**
 * Created by status200 on 2017/9/1.
 */
@Entity
@Table(name = "audit_step", schema = "makerspace", catalog = "")
public class AuditStep {
    private Integer auditStep;
    private String description;

    @Id
    @Column(name = "audit_step", nullable = false)
    public Integer getAuditStep() {
        return auditStep;
    }

    public void setAuditStep(Integer auditStep) {
        this.auditStep = auditStep;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditStep auditStep1 = (AuditStep) o;

        if (auditStep != null ? !auditStep.equals(auditStep1.auditStep) : auditStep1.auditStep != null) return false;
        if (description != null ? !description.equals(auditStep1.description) : auditStep1.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auditStep != null ? auditStep.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    /**
     * 评审标志
     */

    // 未选座
    public static final int NOT_SELECT_SEATS = 30;

    // 未录入考勤号
    public static final int NOT_ENTER_ATTENDANCE_NUMBER = 50;

    // 未签署入驻文件
    public static final int NOT_SIGN_FILE = 60;

    // 未设置账号
    public static final int NOT_SET_ACCOUNT = 65;

    // 已完成入驻
    public static final int TEAM_IN_DONE = 70;


}
