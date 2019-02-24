package com.xmu.makerspace.service;

import com.xmu.makerspace.config.MailConfig;
import com.xmu.makerspace.dao.*;
import com.xmu.makerspace.domain.*;
import com.xmu.makerspace.model.SelectRoomVO;
import com.xmu.makerspace.model.SimpleTeamInfoVO;
import com.xmu.makerspace.model.TeamAccountDTO;
import com.xmu.makerspace.model.attendance.AttendanceNumberDTO;
import com.xmu.makerspace.model.attendance.AttendanceNumberVO;
import com.xmu.makerspace.model.attendance.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by status200 on 2017/9/1.
 */
@Service
public class TeamInService {

    @Value("${makerspace.root-url}")
    private String rootUrl;

    private final TempTeamRepository tempTeamRepository;

    private final TempMemberRepository tempMemberRepository;

    private final FormalMemberRepository formalMemberRepository;

    private final FormalTeamRepository formalTeamRepository;

    private final TempTeamFileService tempTeamFileService;

    private final ApplicationRoundRepository applicationRoundRepository;

    private final StudentRepository studentRepository;

    private final SeatRepository seatRepository;

    private final AuditStepCheckService auditStepCheckService;

    private final EmailService emailService;

    private final TeamEnterRepository teamEnterRepository;

    public TeamInService(TempTeamRepository tempTeamRepository,
                         TempMemberRepository tempMemberRepository,
                         FormalMemberRepository formalMemberRepository,
                         FormalTeamRepository formalTeamRepository,
                         TempTeamFileService tempTeamFileService,
                         ApplicationRoundRepository applicationRoundRepository,
                         StudentRepository studentRepository,
                         SeatRepository seatRepository,
                         AuditStepCheckService auditStepCheckService,
                         EmailService emailService,
                         TeamEnterRepository teamEnterRepository) {
        this.tempTeamRepository = tempTeamRepository;
        this.tempMemberRepository = tempMemberRepository;
        this.formalMemberRepository = formalMemberRepository;
        this.formalTeamRepository = formalTeamRepository;
        this.tempTeamFileService = tempTeamFileService;
        this.applicationRoundRepository = applicationRoundRepository;
        this.studentRepository = studentRepository;
        this.seatRepository = seatRepository;
        this.auditStepCheckService = auditStepCheckService;
        this.emailService = emailService;
        this.teamEnterRepository = teamEnterRepository;
    }

    public FormalTeam findFormalTeamByTeamId(int teamId) {
        return formalTeamRepository.findOne(teamId);
    }

    /**
     * 临时团队确认入驻
     *
     * @param teamId 临时团队id
     */
    public void confirmEnter(String teamId) throws IOException {
        createNewTeam(teamId);

        // 检查选座评审状态
        auditStepCheckService.checkSeatState();
        // 检查考勤号评审状态
        auditStepCheckService.checkAttendanceNumberState();
    }

    /**
     * 创建一个新的团队，包括数据库操作和移动文件。
     *
     * @param teamId
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    protected void createNewTeam(String teamId) throws IOException {
        TempTeam tempTeam = tempTeamRepository.findByTeamId(teamId);
        List<TempMember> tempMembers = tempMemberRepository.findByTeamId(teamId);

        // 创建新的正式团队
        FormalTeam formalTeam = createNewFormalTeam(tempTeam);

        // 创建新学生
        createNewStudent(tempMembers);

        // 创建新成员
        List<FormalMember> formalMembers = createNewFormalMembers(formalTeam.getTeamId(), tempMembers);

        // 删除临时成员
        tempMemberRepository.delete(tempMembers);
        // 删除正式成员
        tempTeamRepository.delete(tempTeam.getTeamId());

        // 移动文件
        tempTeamFileService.moveTempTeamFile(tempTeam.getTeamId(), formalTeam.getTeamId());
    }

    /**
     * 创建新正式团队.
     *
     * @param tempTeam
     * @return
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    protected FormalTeam createNewFormalTeam(TempTeam tempTeam) {

        FormalTeam formalTeam = new FormalTeam();

        // 基本属性
        formalTeam.setProjectName(tempTeam.getProjectName());
        formalTeam.setTeamName(tempTeam.getTeamName());
        formalTeam.setProjectType(tempTeam.getProjectType());
        formalTeam.setBelongField(tempTeam.getBelongField());

        // 长属性
        formalTeam.setWorkFoundation(tempTeam.getWorkFoundation());
        formalTeam.setPlan(tempTeam.getPlan());
        formalTeam.setProjectBrief(tempTeam.getProjectBrief());
        formalTeam.setProjectDerector(tempTeam.getProjectDirector());

        ApplicationRound currentApplicationRound = applicationRoundRepository.findFirstByOrderByBeginDateDesc().get(0);

        // 新团队的id
        // 由于新团队的id需要知道该期团队的数量,因此需要将事务的隔离级别设为防止脏读(READ_COMMITTED),以免后续的团队id设置受到影响
        int formalTeamId = currentApplicationRound.getTermId() * 100 + currentApplicationRound.getTeamNumber();
        formalTeam.setTeamId(formalTeamId);

        // 团队数量加1
        currentApplicationRound.setTeamNumber((short)(currentApplicationRound.getTeamNumber() + 1));
        // 提交
        applicationRoundRepository.save(currentApplicationRound);

        // 注册日期
        formalTeam.setRegisterDate(new Date(new java.util.Date().getTime()));

        // 审核状态为未选择座位
        formalTeam.setAuditStep(AuditStep.NOT_SELECT_SEATS);
        // 提交
        formalTeamRepository.save(formalTeam);

        return formalTeam;
    }

    /**
     * 临时成员转为正式成员.
     *
     * @param formalTeamId
     * @param tempMember
     * @return
     */
    private FormalMember tempMember2FormalMember(int formalTeamId, TempMember tempMember) {
        FormalMember formalMember = new FormalMember();

        formalMember.setCollege(tempMember.getCollege());
        formalMember.setEducationBackground(tempMember.getEducationBackground());
        formalMember.setEmail(tempMember.getEmail());
        formalMember.setCollege(tempMember.getCollege());
        formalMember.setOrderInTeam(tempMember.getOrderInTeam());
        formalMember.setPhoneNumber(tempMember.getPhoneNumber());
        formalMember.setStudentId(tempMember.getStudentId());
        formalMember.setStudentName(tempMember.getStudentName());

        formalMember.setTeamId(formalTeamId);

        return formalMember;
    }

    /**
     * 创建新正式团队的成员
     *
     * @param formalTeamId
     * @return
     */
    @Transactional
    protected List<FormalMember> createNewFormalMembers(int formalTeamId, List<TempMember> tempMembers) {

        List<FormalMember> formalMembers = new ArrayList<>();

        // 创建新团队的成员
        tempMembers.forEach(e -> {
            FormalMember formalMember = tempMember2FormalMember(formalTeamId, e);
            formalMembers.add(formalMember);
            formalMemberRepository.save(formalMember);
        });

        return formalMembers;
    }

    /**
     * 创建新学生.
     *
     * @param tempMembers
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    protected void createNewStudent(List<TempMember> tempMembers) {
        List<Student> students = new ArrayList<>();

        tempMembers.forEach(e -> {
            if (!studentRepository.exists(e.getStudentId())) {
                Student student = new Student();
                student.setStudentId(e.getStudentId());

                students.add(student);
            }
        });

        studentRepository.save(students);
    }

    @Transactional(readOnly = true)
    public List<SelectRoomVO> getRoomStatistic() {
        List<Integer> seats = seatRepository.findDistinctRoomNo();

        List<SelectRoomVO> selectRoomVOS = new ArrayList<>();

        seats.forEach(e -> {
            SelectRoomVO vo = new SelectRoomVO();
            vo.setRoomNo(e);
            vo.setSeatTotal(seatRepository.countByRoomNo(e));
            vo.setSeatUsed(seatRepository.countByRoomNoAndStudentIdNotNull(e));

            selectRoomVOS.add(vo);
        });

        return selectRoomVOS;
    }


    /**
     * 查找所有未录入考勤号的团队
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<AttendanceNumberVO> getAllTeamAttendanceInfo() {
        List<FormalTeam> teams = formalTeamRepository.findByAuditStep(AuditStep.NOT_ENTER_ATTENDANCE_NUMBER);

        List<AttendanceNumberVO> vos = new ArrayList<>();
        teams.forEach(e -> {
            AttendanceNumberVO attendanceNumberVO = new AttendanceNumberVO();

            // 设置两个属性
            attendanceNumberVO.setTeamId(e.getTeamId());
            attendanceNumberVO.setTeamName(e.getTeamName());

            List<FormalMember> formalMembers = formalMemberRepository.findByTeamId(e.getTeamId());
            List<Member> members = new ArrayList<>();

            // 设置团队下的成员
            for (FormalMember formalMember : formalMembers) {
                Member member = new Member();

                // 根据学号查找对应学生,这一步是为了查找成员的考勤号
                Student student = studentRepository.findOne(formalMember.getStudentId());

                member.setStudentId(formalMember.getStudentId());
                member.setStudentName(formalMember.getStudentName());
                member.setAttendanceNumber(student.getAttendanceNumber());
                // 成员加到成员数组中
                members.add(member);
            }
            // 成员加到团队vo中
            attendanceNumberVO.setMembers(members);
            // vo添加到数组中
            vos.add(attendanceNumberVO);

        });

        return vos;
    }

    /**
     * 验证一组考勤号是否有重复的项目
     *
     * @return 重复的考勤号项目
     */
    @Transactional(readOnly = true)
    public List<AttendanceNumberDTO> validateAttendanceNumber(List<AttendanceNumberDTO> dtos) {

        List<AttendanceNumberDTO> duplicateItems = new ArrayList<>();

        dtos.forEach(e -> {
            if (studentRepository.countByAttendanceNumber(e.getAttendanceNumber()) != 0) {
                duplicateItems.add(e);
            }
        });

        return duplicateItems;
    }

    /**
     * 提交一组考勤号。同时会验证该组考勤号是否出现重复的。
     *
     * @param dtos
     * @return
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public List<AttendanceNumberDTO> attendanceNumberSubmit(List<AttendanceNumberDTO> dtos) {

        List<AttendanceNumberDTO> duplicateItem = validateAttendanceNumber(dtos);

        // 没有重复项目,写入数据库
        if (duplicateItem.size() == 0) {

            dtos.forEach(e -> {
                Student student = studentRepository.findOne(e.getStudentId());
                student.setAttendanceNumber(e.getAttendanceNumber());
                studentRepository.save(student);
            });

            auditStepCheckService.checkAttendanceNumberState();

        }

        return duplicateItem;
    }

    /**
     * 获取所有暂未签署入驻文件的团队
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<SimpleTeamInfoVO> getAllTeamNeedToSignFile() {

        List<FormalTeam> formalTeams = formalTeamRepository.findByAuditStep(AuditStep.NOT_SIGN_FILE);
        List<SimpleTeamInfoVO> vos = new ArrayList<>();

        formalTeams.forEach(e -> {
            SimpleTeamInfoVO vo = new SimpleTeamInfoVO();
            vo.setTeamId(e.getTeamId());
            vo.setTeamName(e.getTeamName());

            vos.add(vo);
        });

        return vos;
    }

    /**
     * 入驻文件签署完毕
     *
     * @param teamId 完成该步骤的团队的id
     */
    @Transactional(rollbackFor = Exception.class)
    public void signFileDone(int teamId) throws MessagingException {
        FormalTeam team = formalTeamRepository.findOne(teamId);
        team.setAuditStep(AuditStep.NOT_SET_ACCOUNT);

        formalTeamRepository.save(team);

        // 查找队长,此处为了查找队长邮箱
        FormalMember leader = formalMemberRepository.findByTeamIdAndOrderInTeam(team.getTeamId(), (short) 1);

        HashMap<String, Object> params = new HashMap<>();
        params.put("teamId", team.getTeamId());
        params.put("teamName", team.getTeamName());
        params.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new java.util.Date()));
        params.put("rootUrl", rootUrl);

        emailService.sendTemplateEmail("emailtemplate/notifyTeamToSetAccount.vm", params, "请设置您团队的账号", leader.getEmail());
    }

    /**
     * 设置账号
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void setTeamAccount(TeamAccountDTO dto) {
        TeamEnter teamEnter = new TeamEnter();
        teamEnter.setTeamId(dto.getTeamId());
        teamEnter.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        teamEnter.setLastEnterTime(null);

        FormalTeam formalTeam = formalTeamRepository.findOne(dto.getTeamId());
        // 标志位移动
        formalTeam.setAuditStep(AuditStep.TEAM_IN_DONE);

        formalTeamRepository.save(formalTeam);
        teamEnterRepository.save(teamEnter);
    }
}
