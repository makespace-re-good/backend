package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.*;
import com.xmu.makerspace.domain.*;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TempTeamService {
    @Value("${url}")
    String confirmUrl;

    //发送邮件的功能类
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    TempTeamRepository tempTeamRepository;

    @Value("${urlOfLogin}")
    String urlOfLogin;

    @Value("${makerspace.email.username}")
    String from;

    @Autowired
    TempMemberRepository tempMemberRepository;

    //不知道这里具体是啥功能，记得去查
    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    MesNumRepository mesNumRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FormalTeamRepository formalTeamRepository;

    @Autowired
    FormalMemberRepository formalMemberRepository;

    @Autowired
    TeamEnterRepository teamEnterRepository;

    @Autowired
    ApplicationRoundRepository applicationRoundRepository;
    /**
     * 跳转到确认页面的接口
     * @param httpServletResponse
     * @param httpServletRequest
     * @param teamid
     */
    public void toConfirmUrl(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,String teamid)
    {
        Cookie cookie=new Cookie("teamid",teamid);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        httpServletResponse.addCookie(cookie);
        try {
            httpServletResponse.sendRedirect(confirmUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认入驻
     */
    public String confirmEnter(HttpServletRequest httpServletRequest) throws MessagingException {
            String team_id = "";
            Cookie[] cookies = httpServletRequest.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("teamid")) {
                    team_id = cookies[i].getValue();
                    break;
                }
            }
            //处理团队信息
            TempTeam tempTeam = tempTeamRepository.findByTeamId(team_id);
            FormalTeam formalTeam = new FormalTeam();
            String newTeamid=Integer.toString(applicationRoundRepository.findAll().get(0).getTermId())+Integer.toString(formalTeamRepository.findAll().size()+1);
            for(int i=1;;i++)
            {
                if(formalTeamRepository.findAllByTeamId(Integer.parseInt(newTeamid))==null)
                    break;
                else
                    newTeamid=Integer.toString(Integer.parseInt(newTeamid)+1);
            }
            formalTeam.setTeamId(Integer.parseInt(newTeamid));
            formalTeam.setTeamName(tempTeam.getTeamName());
            formalTeam.setBelongField(tempTeam.getBelongField());
            formalTeam.setPlan(tempTeam.getPlan());
            formalTeam.setProjectBrief(tempTeam.getProjectBrief());
            formalTeam.setProjectDerector(tempTeam.getProjectDirector());
            formalTeam.setProjectName(tempTeam.getProjectName());
            formalTeam.setProjectType(tempTeam.getProjectType());
            formalTeam.setWorkFoundation(tempTeam.getWorkFoundation());
            formalTeam.setAuditStep(30);//刚刚通过申请的团队，尚未分配座位
            // formalTeam.setRegisterDate(System.t);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            formalTeam.setRegisterDate(sqlDate);
            formalTeamRepository.save(formalTeam);
            //团队在消息中心的初始化
            MesNum mesNum = new MesNum();
            mesNum.setMessageNum(0);
            mesNum.setTeamId(Integer.parseInt(newTeamid));
            mesNumRepository.save(mesNum);

            //处理团队成员的信息，数据库结构相同
            ArrayList<TempMember> tempMembers = tempMemberRepository.findAllByTeamId(team_id);
            System.out.println(tempMembers.size());
            for (int i = 0; i < tempMembers.size(); i++) {
                if (studentRepository.findAllByStudentId(tempMembers.get(i).getStudentId()) == null) {
                    Student student = new Student();
                    student.setStudentId(tempMembers.get(i).getStudentId());
                    studentRepository.save(student);
                }
                FormalMember formalMember = new FormalMember();
                formalMember.setCollege(tempMembers.get(i).getCollege());
                formalMember.setEducationBackground(tempMembers.get(i).getEducationBackground());
                formalMember.setEmail(tempMembers.get(i).getEmail());
                formalMember.setOrderInTeam(tempMembers.get(i).getOrderInTeam());
                formalMember.setPhoneNumber(tempMembers.get(i).getPhoneNumber());
                formalMember.setStudentId(tempMembers.get(i).getStudentId());
                formalMember.setStudentName(tempMembers.get(i).getStudentName());
                formalMember.setTeamId(Integer.parseInt(newTeamid));
                formalMemberRepository.save(formalMember);
                //处理团队的登录信息
                TeamEnter teamEnter = new TeamEnter();
                teamEnter.setLastEnterTime(Timestamp.valueOf("2011-11-11 11:11:11"));
                teamEnter.setTeamId(Integer.parseInt(newTeamid));
                teamEnter.setPassword("123456");
                teamEnterRepository.save(teamEnter);
            }

            //发送邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(tempMemberRepository.findByTeamIdAndOrderInTeam(team_id, (short) 1).getEmail());
            System.out.println(tempMemberRepository.findByTeamIdAndOrderInTeam(team_id, (short) 1).getEmail());
            mimeMessageHelper.setSubject("演武创客中心入驻确定");
            HashMap<String, Object> map = new HashMap<>();
            map.put("teamName", tempTeamRepository.findByTeamId(team_id).getTeamName());
            map.put("account",newTeamid);
            map.put("urlOfLogin", urlOfLogin);

            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "emailtemplate/notifyTeamToConfirmEnter.vm", "utf-8", map);
            mimeMessageHelper.setText(text, true);
            mailSender.send(mimeMessage);
            //修改旧的临时团队表中的信息，不删除，只是修改状态
            TempTeam tempTeam1 = tempTeamRepository.findByTeamId(team_id);
            tempTeam1.setAuditOpinion("已入驻");
            tempTeamRepository.save(tempTeam1);
            return "true";
    }
}
