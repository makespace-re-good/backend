package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.TempMember;
import com.xmu.makerspace.domain.TempMemberPK;
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
public interface TempMemberRepository extends CrudRepository<TempMember,TempMemberPK> {

    List<TempMember> findByTeamId(String teamId);

    TempMember findByTeamIdAndOrderInTeam(String teamId, short orderInTeam);

    @Transactional
    @Modifying
    void deleteAllByTeamId(String teamId);

    @Query(value = "SELECT * FROM temp_member WHERE team_id=?1 AND order_in_team=1",nativeQuery = true)
    TempMember findCaptain(String team_id);
    ArrayList<TempMember> findAllByTeamId(String team_id);

    ArrayList<TempMember> findAllByTeamIdOrderByOrderInTeamAsc(String teamid);
}
