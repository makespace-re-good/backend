package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FormalMember;
import com.xmu.makerspace.domain.FormalMemberPK;
import com.xmu.makerspace.domain.FormalTeam;
import com.xmu.makerspace.model.attendance.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/9/1.
 */
public interface FormalMemberRepository extends CrudRepository<FormalMember,FormalMemberPK> {

    ArrayList<FormalMember> findByTeamId(int teamId);

    int countByStudentId(String studentId);

    FormalMember findByTeamIdAndOrderInTeam(int teamId, short orderInTeam);
    List<FormalMember> findAll();

    @Modifying
    @Transactional
    void deleteAllByStudentIdAndTeamId(String student_id,int team_id);

    FormalMember findAllByStudentIdAndTeamId(String student_id,int team_id);

    FormalMember findFirstByStudentId(String studentid);


}
