package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.TempMemberRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.domain.TempMember;
import com.xmu.makerspace.domain.TempTeam;
import com.xmu.makerspace.model.RegisterSubmitDTO;
import com.xmu.makerspace.model.TempMemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提交申请页面的Service.
 *
 * Created by status200 on 2017/8/9.
 */
@Service
public class RegisterService {

    private final TempTeamRepository tempTeamRepository;

    private final TempMemberRepository tempMemberRepository;

    private final TempTeamFileService tempTeamFileService;

    @Autowired
    public RegisterService(TempTeamRepository tempTeamRepository, TempMemberRepository tempMemberRepository, TempTeamFileService tempTeamFileService) {
        this.tempTeamRepository = tempTeamRepository;
        this.tempMemberRepository = tempMemberRepository;
        this.tempTeamFileService = tempTeamFileService;
    }

    // TODO:加上对正式团队表的查询
    @Transactional(readOnly = true)
    public boolean validateTeamName(String teamName,String teamId) {
        return tempTeamRepository.countByTeamNameAndTeamIdNot(teamName,teamId) == 0;
    }

    /**
     * 将提交上来的结果提交到数据库
     * @param data DTO数据对象
     */
    @Transactional
    public void submit(RegisterSubmitDTO data) {
        TempTeam tempTeam = submitDTO2TempTeam(data);

        tempTeamRepository.save(tempTeam);

        List<TempMember> members = new ArrayList<>();
        data.getMembers().forEach(e -> members.add(RegisterService.tempMemberDTO2TempMember(e)));

        // 添加此次提交的成员
        tempMemberRepository.save(members);
    }

    /**
     * 根据团队的id将上传的文件保存到对应目录.
     * 实际上是调用了TempTeamFileService的方法.
     *
     *
     * @param file 上传的文件
     * @param teamId 临时团队id
     * @throws IOException
     *
     * @see TempTeamFileService
     */
    public void saveFile(MultipartFile file, String teamId) throws IOException {
        tempTeamFileService.save(file,teamId);
    }

    /**
     * 根据团队的id删除对应的文件.
     * 实际上调用了TempTeamFileService的方法.
     *
     * @param fileName 要删除的文件名
     * @param teamId 临时团队id
     *
     * @see TempTeamFileService
     */
    public void deleteFile(String fileName, String teamId) {
        tempTeamFileService.delete(fileName,teamId);
    }

    /**
     * 获取一个团队已经上传的所有文件的文件名.
     * 实际上调用TempTeamFileService的方法.
     *
     * @param teamId 临时团队id
     *
     * @see TempTeamFileService
     */
    public List<String> listAllFileNamesOfTeam(String teamId) {
        return tempTeamFileService.listTeamFileName(teamId);
    }


    /**
     * 将TempMemberDTO转为TempMember实体.
     *
     * @param dto Dto对象
     * @return 转换后的实体
     */
    public static TempMember tempMemberDTO2TempMember(TempMemberDTO dto) {
        TempMember tempMember = new TempMember();
        tempMember.setCollege(dto.getCollege());
        tempMember.setEducationBackground(dto.getEducationBackground());
        tempMember.setEmail(dto.getEmail());
        tempMember.setOrderInTeam((short)dto.getOrderInTeam());
        tempMember.setPhoneNumber(dto.getPhoneNumber());
        tempMember.setStudentId(dto.getStudentId());
        tempMember.setStudentName(dto.getStudentName());
        tempMember.setTeamId(dto.getTeamId());

        return tempMember;
    }

    /**
     * 将上传过来的DTO对象转为TempTeam实体.
     * 添加了部分非DTO中的属性.
     *
     * @param dto Dto对象
     * @return 转换后的实体
     */
    public static TempTeam submitDTO2TempTeam(RegisterSubmitDTO dto) {
        TempTeam tempTeam = new TempTeam();
        tempTeam.setBelongField(dto.getBelongField());
        tempTeam.setPlan(dto.getPlan());
        tempTeam.setProjectBrief(dto.getProjectBrief());
        tempTeam.setProjectDirector(dto.getProjectDirector());
        tempTeam.setProjectName(dto.getProjectName());
        tempTeam.setProjectType(dto.getProjectType());
        tempTeam.setTeamId(dto.getTeamId());
        tempTeam.setTeamName(dto.getTeamName());
        tempTeam.setWorkFoundation(dto.getWorkFoundation());

        // 添加提交日期
        tempTeam.setSubmitDate(new java.sql.Date(new java.util.Date().getTime()));

//        Calendar cal = Calendar.getInstance();
//        cal.setTime(tempTeam.getSubmitDate());
//        cal.add(Calendar.DATE, 7);

        // 添加审核意见
        tempTeam.setAuditOpinion("未审核");

        // 这里没有设置enter_due_date属性,因为这个属性要在管理员同意之后才会有

        return tempTeam;
    }
}
