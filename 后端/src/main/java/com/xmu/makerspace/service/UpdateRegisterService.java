package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.TempMemberRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.domain.TempMember;
import com.xmu.makerspace.domain.TempTeam;
import com.xmu.makerspace.model.RegisterSubmitDTO;
import com.xmu.makerspace.model.TempMemberDTO;
import com.xmu.makerspace.model.UpdateRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/8/9.
 */
@Service
public class UpdateRegisterService {

    private final TempTeamRepository tempTeamRepository;

    private final TempMemberRepository tempMemberRepository;

    private final TempTeamFileService tempTeamFileService;

    @Autowired
    public UpdateRegisterService(TempTeamRepository tempTeamRepository, TempMemberRepository tempMemberRepository, TempTeamFileService tempTeamFileService) {
        this.tempTeamRepository = tempTeamRepository;
        this.tempMemberRepository = tempMemberRepository;
        this.tempTeamFileService = tempTeamFileService;
    }

    /**
     * 从数据库读取团队已经提交了的申请信息.
     *
     * @param teamId 临时团队id
     * @return 包含了申请信息的对象
     */
    @Transactional(readOnly = true)
    public UpdateRegisterVO getTeamSubmitInfo(String teamId) {
        TempTeam tempTeam = tempTeamRepository.findByTeamId(teamId);
        List<TempMember> members = tempMemberRepository.findByTeamId(teamId);
        List<String> files = tempTeamFileService.listTeamFileName(teamId);

        return genUpdateRegisterVO(tempTeam, members, files);

    }

    /**
     * 查询该团队是否存在
     *
     * @param teamId 临时团队id
     * @return 如果存在返回true,否则返回false.
     */
    @Transactional(readOnly = true)
    public boolean checkIfTeamExists(String teamId) {
        return tempTeamRepository.exists(teamId);
    }

    /**
     * 提交修改的结果.
     *
     * @param data 封装了数据的dto对象
     */
    @Transactional
    public void submit(RegisterSubmitDTO data) {
        TempTeam tempTeam = RegisterService.submitDTO2TempTeam(data);
        tempTeamRepository.save(tempTeam);

        List<TempMember> members = new ArrayList<>();
        data.getMembers().forEach(e -> {
            members.add(RegisterService.tempMemberDTO2TempMember(e));
        });

        // 因为队长会变,成员也会变,于是采用最粗暴的办法,先删光所有成员再添加此次提交上来的成员
        // 先删除掉该团队的所有成员
        tempMemberRepository.deleteAllByTeamId(data.getTeamId());

        // 添加此次提交的成员
        tempMemberRepository.save(members);
    }

    /**
     * 产生一个返回给前台的VO对象.
     *
     * @param tempTeam
     * @param members
     * @param files
     * @return
     */
    public static UpdateRegisterVO genUpdateRegisterVO(TempTeam tempTeam, List<TempMember> members,List<String> files) {
        UpdateRegisterVO vo = new UpdateRegisterVO();

        vo.setWorkFoundation(tempTeam.getWorkFoundation());
        vo.setTeamName(tempTeam.getTeamName());
        vo.setTeamId(tempTeam.getTeamId());
        vo.setProjectName(tempTeam.getProjectName());
        vo.setProjectDirector(tempTeam.getProjectDirector());
        vo.setProjectType(tempTeam.getProjectType());
        vo.setProjectBrief(tempTeam.getProjectBrief());
        vo.setPlan(tempTeam.getPlan());
        vo.setBelongField(tempTeam.getBelongField());
        vo.setFiles(files);

        List<TempMemberDTO> memberDTOS = new ArrayList<>();
        members.forEach(e->{
            memberDTOS.add(tempMember2DTO(e));
        });

        vo.setMembers(memberDTOS);

        return vo;
    }

    public static TempMemberDTO tempMember2DTO(TempMember tempMember) {
        TempMemberDTO dto = new TempMemberDTO();

        dto.setCollege(tempMember.getCollege());
        dto.setEducationBackground(tempMember.getEducationBackground());
        dto.setEmail(tempMember.getEmail());
        dto.setOrderInTeam(tempMember.getOrderInTeam());
        dto.setPhoneNumber(tempMember.getPhoneNumber());
        dto.setStudentId(tempMember.getStudentId());
        dto.setStudentName(tempMember.getStudentName());
        dto.setTeamId(tempMember.getTeamId());

        return dto;
    }
}
