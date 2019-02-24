package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.FormalMemberRepository;
import com.xmu.makerspace.dao.FormalTeamRepository;
import com.xmu.makerspace.dao.SeatRepository;
import com.xmu.makerspace.dao.StudentRepository;
import com.xmu.makerspace.domain.AuditStep;
import com.xmu.makerspace.domain.FormalMember;
import com.xmu.makerspace.domain.FormalTeam;
import com.xmu.makerspace.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by status200 on 2017/9/14.
 *
 * 提供各种检查评审标志位的功能,一般都是在入驻流程某次表单提交后执行。
 */
@Service
public class AuditStepCheckService {

    private final SeatRepository seatRepository;

    private final FormalTeamRepository formalTeamRepository;

    private final FormalMemberRepository formalMemberRepository;

    private final StudentRepository studentRepository;

    @Autowired
    public AuditStepCheckService(SeatRepository seatRepository, FormalTeamRepository formalTeamRepository, FormalMemberRepository formalMemberRepository, StudentRepository studentRepository) {
        this.seatRepository = seatRepository;
        this.formalTeamRepository = formalTeamRepository;
        this.formalMemberRepository = formalMemberRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * 检查所有团队与选座有关的评审状态。
     */
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED)
    public void checkSeatState() {
        // 查询未选座的团队
        List<FormalTeam> formalTeams = formalTeamRepository.findByAuditStep(AuditStep.NOT_SELECT_SEATS);

        // 遍历所有团队
        // 如果一个团队拥有座位的成员数等于成员总数，就说明该团队已经选座完毕
        formalTeams.forEach(e->{
            // 拥有座位的成员数
            int numOfMemberHasSeat = 0;
            // 查询所有成员
            List<FormalMember> members = formalMemberRepository.findByTeamId(e.getTeamId());

            // 遍历所有成员,查询是否选座
            for (FormalMember member : members) {
                numOfMemberHasSeat += seatRepository.countByStudentId(member.getStudentId());
            }

            // 拥有座位的成员数等于成员总数,修改评审状态为未录入考勤号
            if (numOfMemberHasSeat == members.size()) {
                e.setAuditStep(AuditStep.NOT_ENTER_ATTENDANCE_NUMBER);
                formalTeamRepository.save(e);
            }
        });
    }

    /**
     * 检查所有团队与考勤号有关的评审状态
     */
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED)
    public void checkAttendanceNumberState() {
        // 查找所有未录入考勤号的团队
        List<FormalTeam> formalTeams = formalTeamRepository.findByAuditStep(AuditStep.NOT_ENTER_ATTENDANCE_NUMBER);

        // 遍历所有团队
        // 如果一个团队有考勤号的成员数等于成员总数,说明该团队考勤号已经录入完毕
        formalTeams.forEach(e->{

            // 拥有考勤号的成员数
            int numOfMemberHasAttendanceNumber = 0;

            // 查找所有成员
            List<FormalMember> members = formalMemberRepository.findByTeamId(e.getTeamId());

            for(FormalMember member : members) {
                // 成员的考勤号不为空
                numOfMemberHasAttendanceNumber += studentRepository.countByStudentIdAndAttendanceNumberNotNull(member.getStudentId());
            }

            // 拥有考勤号的成员数等于成员总数，修改评审状态
            if (numOfMemberHasAttendanceNumber == members.size()) {
                e.setAuditStep(AuditStep.NOT_SIGN_FILE);
                formalTeamRepository.save(e);
            }
        });
    }
}
