package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FormalTeam;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/9/1.
 */
public interface FormalTeamRepository extends CrudRepository<FormalTeam,Integer> {

    List<FormalTeam> findByAuditStep(int auditStep);

    ArrayList<FormalTeam> findAll();
    FormalTeam findAllByTeamId(int teamId);

    @Query(value = "UPDATE formal_team set BELONG_FIELD='231'  WHERE TEAM_ID=?5",nativeQuery = true)
    @Modifying
    @Transactional
    void changeTeam(String belong_field, String project_director, String work_foundation, String plan, Integer team_id);
}
