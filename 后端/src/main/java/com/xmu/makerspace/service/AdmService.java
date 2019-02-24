package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.AdminEnterRepository;
import com.xmu.makerspace.dao.ApplicationRoundRepository;
import com.xmu.makerspace.domain.ApplicationRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class AdmService {
    @Autowired
    AdminEnterRepository adminEnterRepository;
    @Autowired
    ApplicationRoundRepository applicationRoundRepository;
    public int admLogin(String account,String code,String date)
    {
            if(code.equals(adminEnterRepository.findByAdminAccount(account).getPasswordCode()))
            {
                adminEnterRepository.AdmLogin(Date.valueOf(date),account);
                return adminEnterRepository.findByAdminAccount(account).getAuthority();
            }
            else {
                System.out.println(adminEnterRepository.findByAdminAccount(account).getPasswordCode());
                return -1;
            }
    }
    @Transactional//事务回滚
    public boolean editApplicationRound(String beginDate,String endDate,int termId)
    {
        try {
            ApplicationRound applicationRound = applicationRoundRepository.findAll().get(0);
            applicationRound.setBeginDate(Date.valueOf(beginDate));
            applicationRound.setEndDate(Date.valueOf(endDate));
            applicationRound.setTermId(termId);
            applicationRoundRepository.save(applicationRound);
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
