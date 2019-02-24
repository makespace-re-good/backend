package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.ApplicationRoundRepository;
import com.xmu.makerspace.domain.ApplicationRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by status200 on 2017/8/31.
 *
 * 检查申请是否开放
 */
@Service
public class ApplicationRoundCheckService {

    @Autowired
     ApplicationRoundRepository applicationRoundRepository;

//    @Autowired
//    public ApplicationRoundCheckService(ApplicationRoundRepository applicationRoundRepository) {
//        this.applicationRoundRepository = applicationRoundRepository;
//    }

    /**
     * 检查申请是否开放
     *
     * @return 如果开放,返回true;如果申请未开放,返回false
     */
    public boolean isApplicationOpen() {
        List<ApplicationRound> applicationRounds = applicationRoundRepository.findFirstByOrderByBeginDateDesc();

        if(applicationRounds.size() == 0) {
            return false;
        }

        ApplicationRound currentApplication = applicationRounds.get(0);

        Date beginDate = currentApplication.getBeginDate();
        Date endDate = currentApplication.getEndDate();

        Date currentDate = new Date();

        // beginDate <= currentDate < endDate
        return beginDate.getTime() <= currentDate.getTime() && currentDate.getTime() <= endDate.getTime();
    }

}
