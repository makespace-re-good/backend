package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.ApplicationRound;
import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface ApplicationRoundRepository extends CrudRepository<ApplicationRound,Integer> {
    List<ApplicationRound> findFirstByOrderByBeginDateDesc();
   // ApplicationRound findFirst();
    ArrayList<ApplicationRound> findAll();

    void findAllByBeginDate(Date time);
}
