package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.AdminEnter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by 35894 on 2017/7/29.
 */
public interface AdminEnterRepository extends CrudRepository<AdminEnter,Integer>{

    AdminEnter findByAdminAccount(String adminAccount);
    @Modifying
    @Transactional
    @Query(value = "UPDATE admin_enter SET last_enter_time=?1 WHERE  admin_account=?2",nativeQuery = true)
    void AdmLogin(Date time, String account);
}
