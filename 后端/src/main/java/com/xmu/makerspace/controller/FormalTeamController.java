package com.xmu.makerspace.controller;

import com.xmu.makerspace.domain.FormalMember;
import com.xmu.makerspace.domain.Request;
import com.xmu.makerspace.model.SeatDTO;
import com.xmu.makerspace.model.SeatMessage;
import com.xmu.makerspace.service.FormalTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 对正式团队的处理
 */

@CrossOrigin
@RestController
@RequestMapping("/formalTeam")
public class FormalTeamController {
    @Autowired
    FormalTeamService formalTeamService;

    /**
     * 将座位与学生绑定
     * @param student_id
     * @param team_id
     * @param root_no
     * @param seat_no
     * @return
     */
    @RequestMapping("/chooseSeat")
    public boolean chooseSeat(String student_id,int team_id,int root_no,int seat_no)
    {
        return formalTeamService.chooseSeat(student_id,team_id,root_no,seat_no);
    }

    /**
     * 获取未被分配的座位的列表
     * @return
     */
    @RequestMapping("/getSeatWithoutStu")
    public ArrayList<SeatMessage> getSeatList()
    {
        return formalTeamService.getSeatList();
    }

    /**
     * 获取未分配座位的成员的信息列表
     * @param team_id
     * @return
     */
    @RequestMapping("/getStuWithoutSeat")
    public ArrayList<FormalMember> getMemberWithoutSeat(int team_id)
    {
        return formalTeamService.getMemberWithoutSeat(team_id);
    }
    /**
     * 获取未被分配考勤号的成员
     */
    @RequestMapping("/getStuWithoutAtt")
    public ArrayList<FormalMember> getStuWithoutAtt(int team_id)
    {
        return formalTeamService.getMemberWithoutAtt(team_id);
    }

    /**
     * 分配考勤号
     */
    @RequestMapping("/chooseAtt")
    public boolean chooseAtt(String student_id,int att)
    {
        return formalTeamService.chooseAtt(student_id,att);
    }
    /**
     * 提交反馈
     */
    @RequestMapping("/submitRequest")
    public boolean submitRequest(String team_id,String title,String content,int type)
    {
        return formalTeamService.submitRequest(team_id,title,content,type);
    }

    /**
     * 获取反馈的列表
     * @param team_id
     * @return
     */
    @RequestMapping("/getRequestList")
    public ArrayList<Request> getRequestList(String team_id)
    {
        return formalTeamService.getRequestList(team_id);
    }

    /**
     * 获取所有反馈的列表
     */
    @RequestMapping("/getAllRequestList")
    public ArrayList<Request> getAllRequestList()
    {
        return formalTeamService.getAllRequestList();
    }

    /**
     * 处理反馈
     */
    @RequestMapping("/dealRequest")
    public boolean dealRequest(String request_id,String team_id)
    {
        return formalTeamService.dealRequest(request_id,team_id);
    }
    /**
     * 获取需要分配座位的队伍个数
     */
    @RequestMapping("/getNumOfTeamSeat")
    public int numOfTeamSeat()
    {
        return formalTeamService.numOfTeamNeedSeat();
    }
    /**
     * 获取需要分配考勤号的队伍个数
     */
    @RequestMapping("/getNumOfTeamAtt")
    public int numOfTeamAtt()
    {
        return formalTeamService.numOfTeamNeedAtt();
    }


    /**
     * 团队登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public boolean login(String account,String password)
    {
        System.out.println(account);
        return formalTeamService.login(Integer.parseInt(account),password);
    }

    /**
     * 获取座位信息
     * @param room_no
     * @param
     * @return
     */
    @RequestMapping("getSeatMes")
    public ArrayList<SeatDTO> getSeatMes(String room_no,String team_id)
    {
        System.out.println(team_id);
        return formalTeamService.getSeatMes(room_no,team_id);
    }

    /**
     * 考勤号查重
     * @param att_num
     * @return
     */
    @RequestMapping("checkAttnum")
    public boolean checkAttnum(int att_num)
    {
        return formalTeamService.checkAttendanceRepeat(att_num);
    }

    /**
     * 团队修改密码
     * @param teamid
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("changePassword")
    public boolean changePassword(String teamid,String oldPassword,String newPassword)
    {
        return formalTeamService.changePassword(teamid,oldPassword,newPassword);
    }

}
