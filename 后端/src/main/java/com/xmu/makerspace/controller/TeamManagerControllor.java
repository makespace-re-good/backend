package com.xmu.makerspace.controller;

import com.xmu.makerspace.dao.FormalTeamRepository;
import com.xmu.makerspace.dao.RefusereasonRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.model.ManageRegisterVO;
import com.xmu.makerspace.model.Register;
import com.xmu.makerspace.model.Team.*;
import com.xmu.makerspace.model.TeamAgainVO;
import com.xmu.makerspace.service.TeamManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/TeamManagement")
@CrossOrigin
public class TeamManagerControllor {
    @Autowired
    TeamManagementService teamManagementService;
    @Autowired
    TempTeamRepository tempTeamRepository;
    @Autowired
    RefusereasonRepository refusereasonRepository;
    @Autowired
    FormalTeamRepository formalTeamRepository;

    @Value("${url}")
    String confirmUrl;
    /**
     * 获取正式团队列表
     * @param page
     * @return
     */
    @RequestMapping("/getTeamList")
    public TeamListVO getTeamList(int page)
    {
        return  teamManagementService.GetTeamList(page);
    }

    /**
     * 获取正式团队详细信息
     * @param team_id
     * @return
     */
    @RequestMapping("/getTeamMore")
    public TeamMore getTeamMore(String team_id)
    {
        return teamManagementService.getTeamDetail(Integer.parseInt(team_id));
    }

    /**
     * 更换团队成员信息
     * @param student_id
     * @param room_no
     * @param seat_no
     * @param attendance_number
     * @return
     */
    @RequestMapping("/changeMember")
    public boolean changeMemberMessage(String student_id,int team_id,int room_no,int seat_no,int attendance_number)
    {
        return teamManagementService.changeMemberMessage(student_id,team_id,room_no,seat_no,attendance_number);
    }

    /**
     * 删除团队成员
     * @param team_id
     * @param student_id
     * @return
     */
    @RequestMapping("/deleteMember")
    public boolean deleteMember(String student_id,int team_id)
    {
        return teamManagementService.deleteMember(student_id,team_id);
    }
    /**
     * 获取申请队伍和申请失败的队伍的信息列表
     */
    @RequestMapping("/getTempTeamList")
    public TempTeamList getList(int page)
    {
        return teamManagementService.getTempTeamList(page);
    }
    /**
     * 接受或拒绝团队的申请
     */
    @RequestMapping("/dealTeam")
    public boolean dealTeam(String team_id,int choice,String reason) throws MessagingException {
        return teamManagementService.dealTempTeam(team_id,choice,reason);
    }

    /**
     * 获取临时团队的详细信息
     */
    @RequestMapping("/TempTeamMore")
    public TempTeamMore getTempTeamMore(String team_id,HttpServletResponse httpServletResponse)
    {
        return teamManagementService.getTempTeamMore(team_id,httpServletResponse);
    }


    /**
     * 修改团队信息
     */
    @RequestMapping("/changeTeam")
    public boolean changeTeam(int team_id,String project_brief,String project_derector,String word_foundation,String plan)
    {
        return teamManagementService.changeTeam(team_id,project_brief,project_derector,word_foundation,plan);
    }

    /**
     * 申请团队，提交申请资料
     */
    @PostMapping("/submitNewTeam")
    public Register submitNewTeam(@RequestBody NewTeam newTeam, HttpServletResponse httpServletResponse) throws MessagingException {
        return teamManagementService.submitTeam(newTeam,httpServletResponse);
    }

    /**
     * 再次提交申请
     *
     * @param newTeam
     * @return
     */
    @PostMapping("/submitAgain")
    public Register submitAgain(@RequestBody TeamAgainVO newTeam) {
        return teamManagementService.submitTeamAgain(newTeam,newTeam.getTeamid());
    }


    /**
     * 申请是否开放
     * @return
     */
    @RequestMapping("/isOpen")
    @ResponseBody
    public boolean isOpen()
    {
        return teamManagementService.isOpen();
    }

    /**
     * 查看团队状态
     * @param team_id
     * @return
     */
    @RequestMapping("/check")
    public String check(String team_id,HttpServletResponse httpServletResponse) throws IOException {
        Cookie cookie=new Cookie("teamid",team_id);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        httpServletResponse.addCookie(cookie);
        if(refusereasonRepository.findOne(team_id)!=null)
            return refusereasonRepository.findOne(team_id).getReason();
        else if(tempTeamRepository.findByTeamId(team_id).getAuditOpinion().equals("已同意"))
        {
            return "yes";//已同意
        }
        else if(tempTeamRepository.findByTeamId(team_id).getAuditOpinion().equals("未处理"))
            return "未处理";
        else  if(formalTeamRepository.findAllByTeamId(Integer.parseInt(team_id))!=null)
            return "ok";//已入驻
        else
            return "查询码错误";
    }
}
