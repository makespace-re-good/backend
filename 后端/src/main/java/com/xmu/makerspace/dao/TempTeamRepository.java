package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.TempTeam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/8/9.
 */
public interface TempTeamRepository extends CrudRepository<TempTeam,String> {

    int countByTeamNameAndTeamIdNot(String teamName, String teamId);

    TempTeam findByTeamId(String teamId);

    ArrayList<TempTeam> findAll();

    ArrayList<TempTeam> findByAuditOpinionOrderBySubmitDateDesc(String auditOption);

    @Modifying
    @Transactional
    @Query(value = "UPDATE temp_team SET audit_opinion='已拒绝' WHERE team_id=?1",nativeQuery = true)
    void refuseTeam(String team_id);
}
