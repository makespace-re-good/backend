package com.xmu.makerspace.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by status200 on 2017/7/29.
 */
@Entity
@Table(name = "admin_enter", schema = "makerspace", catalog = "")
public class AdminEnter {
    private Integer adminId;
    private String adminAccount;
    private String passwordCode;
    private Integer authority;
    private Timestamp lastEnterTime;

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Id
    @Column(name = "admin_id", nullable = false)
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "admin_account", nullable = true, length = 30)
    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    @Basic
    @Column(name = "password_code", nullable = true, length = 255)
    public String getPasswordCode() {
        return passwordCode;
    }

    public void setPasswordCode(String passwordCode) {
        this.passwordCode = passwordCode;
    }

    @Basic
    @Column(name = "authority", nullable = true)
    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    @Basic
    @Column(name = "last_enter_time", nullable = true)
    public Timestamp getLastEnterTime() {
        return lastEnterTime;
    }

    public void setLastEnterTime(Timestamp lastEnterTime) {
        this.lastEnterTime = lastEnterTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEnter that = (AdminEnter) o;

        if (adminId != null ? !adminId.equals(that.adminId) : that.adminId != null) return false;
        if (adminAccount != null ? !adminAccount.equals(that.adminAccount) : that.adminAccount != null) return false;
        if (passwordCode != null ? !passwordCode.equals(that.passwordCode) : that.passwordCode != null) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;
        if (lastEnterTime != null ? !lastEnterTime.equals(that.lastEnterTime) : that.lastEnterTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminId != null ? adminId.hashCode() : 0;
        result = 31 * result + (adminAccount != null ? adminAccount.hashCode() : 0);
        result = 31 * result + (passwordCode != null ? passwordCode.hashCode() : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (lastEnterTime != null ? lastEnterTime.hashCode() : 0);
        return result;
    }
}
