package com.xmu.makerspace.service;

import com.xmu.makerspace.config.MailConfig;
import com.xmu.makerspace.dao.TempMemberRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.domain.TempMember;
import com.xmu.makerspace.domain.TempTeam;
import com.xmu.makerspace.model.ManageRegisterVO;
import com.xmu.makerspace.model.TempMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by status200 on 2017/8/23.
 */
@Service
public class ManageRegisterService {

    @Value("${makerspace.root-url}")
    private String rootUrl;

    private final TempTeamRepository tempTeamRepository;

    private final TempMemberRepository tempMemberRepository;

    private final TempTeamFileService tempTeamFileService;

    private final EmailService emailService;

    @Autowired
    public ManageRegisterService(TempTeamRepository tempTeamRepository, TempMemberRepository tempMemberRepository,
                                 TempTeamFileService tempTeamFileService, EmailService emailService) {
        this.tempTeamRepository = tempTeamRepository;
        this.tempMemberRepository = tempMemberRepository;
        this.tempTeamFileService = tempTeamFileService;
        this.emailService = emailService;
    }

    /**
     * 获取所有团队的申请信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ManageRegisterVO> getAllRegistration() {
        List<TempTeam> tempTeams = tempTeamRepository.findAll();

        List<ManageRegisterVO> vos = new ArrayList<>();

        // 遍历所有团队，找出所有团队的成员、上传的文件
        tempTeams.forEach(e -> {
            List<TempMemberDTO> members = new ArrayList<>();

            // 找出团队对应的成员，并将其转换成DTO对象
            tempMemberRepository.findByTeamId(e.getTeamId()).forEach(e1 -> {
                // 转为DTO对象
                members.add(UpdateRegisterService.tempMember2DTO(e1));
            });

            vos.add(genManageRegisterVO(e, members, tempTeamFileService.listTeamFileName(e.getTeamId())));
        });

        return vos;
    }

    /**
     * 同意团队的申请。同时发送邮件通知团队的负责人。
     *
     * @param teamId
     */
    @Transactional(rollbackFor = Exception.class )
    public void agreeRegistration(String teamId) throws MessagingException {

        TempTeam tempTeam = tempTeamRepository.findByTeamId(teamId);

        tempTeam.setAuditOpinion("已同意");

        // 设置确认截止的日期为一周以后
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 7);
        tempTeam.setEnterDueDate(new java.sql.Date(cal.getTime().getTime()));

        tempTeamRepository.save(tempTeam);

        // 发送邮件通知团队确认
        HashMap<String, Object> params = new HashMap<>();
        params.put("teamName", tempTeam.getTeamName());
        params.put("teamId", tempTeam.getTeamId());
        params.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        // 网站的根路径
        params.put("rootUrl", rootUrl);
        // 找到负责人
        TempMember leader = tempMemberRepository.findByTeamIdAndOrderInTeam(tempTeam.getTeamId(), (short) 1);

        emailService.sendTemplateEmail("emailtemplate/notifyTeamToConfirmEnter.vm", params, "演武创客空间入驻确认", leader
                .getEmail());
    }

    /**
     * 拒绝团队的申请。同时通知团队的负责人。
     *
     * @param teamId 团队id
     * @throws MessagingException
     */
    @Transactional(rollbackFor = Exception.class )
    public void refuseRegistration(String teamId) throws MessagingException {
        TempTeam tempTeam = tempTeamRepository.findByTeamId(teamId);

        tempTeam.setAuditOpinion("已拒绝");

        tempTeamRepository.save(tempTeam);

        // 发送邮件告知团队被拒绝
        HashMap<String, Object> params = new HashMap<>();
        params.put("teamName", tempTeam.getTeamName());
        params.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        // 找到负责人
        TempMember leader = tempMemberRepository.findByTeamIdAndOrderInTeam(tempTeam.getTeamId(), (short) 1);

        emailService.sendTemplateEmail("emailtemplate/refuseTeamToEnter.vm", params, "您未能获得创客空间入驻资格",
                leader.getEmail());
    }

    @Transactional(readOnly = true)
    public TempTeam findTempTeamByTeamId(String teamId) {
        return tempTeamRepository.findByTeamId(teamId);
    }

    /**
     * 产生ManageRegisterVO对象。该方法将临时团队和临时团队成员以及上传的文件数组组合成一个对象。
     *
     * @param tempTeam
     * @param members
     * @param files
     * @return
     */
    public static ManageRegisterVO genManageRegisterVO(TempTeam tempTeam, List<TempMemberDTO> members, List<String>
            files) {
        ManageRegisterVO vo = new ManageRegisterVO();

        vo.setTeamId(tempTeam.getTeamId());
        vo.setTeamName(tempTeam.getTeamName());
        vo.setBelongField(tempTeam.getBelongField());
        vo.setProjectName(tempTeam.getProjectName());
        vo.setProjectType(tempTeam.getProjectType());
        vo.setMembers(members);

        vo.setProjectBrief(tempTeam.getProjectBrief());
        vo.setProjectDirector(tempTeam.getProjectDirector());
        vo.setWorkFoundation(tempTeam.getWorkFoundation());
        vo.setPlan(tempTeam.getPlan());

        vo.setAuditOpinion(tempTeam.getAuditOpinion());
        vo.setFiles(files);
        vo.setSubmitDate(tempTeam.getSubmitDate());

        return vo;
    }
}
