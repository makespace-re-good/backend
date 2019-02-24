package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.*;
import com.xmu.makerspace.domain.*;
import com.xmu.makerspace.model.NumTempTeamDTO;
import com.xmu.makerspace.model.Register;
import com.xmu.makerspace.model.Team.*;
import com.xmu.makerspace.model.TeamAgainVO;
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
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Service
public class TeamManagementService {
    @Autowired
    MesNumRepository mesNumRepository;
    @Autowired
    FormalMemberRepository formalMemberRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TempTeamRepository tempTeamRepository;
    @Autowired
    TempMemberRepository tempMemberRepository;
    @Autowired
    ApplicationRoundRepository applicationRoundRepository;
    @Autowired
    RefusereasonRepository refusereasonRepository;
    @Autowired
    TeamEnterRepository teamEnterRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;
    @Value("${toConfirmUrl}")
    String toConfirmUrl;
    @Autowired
    FilePathRepository filePathRepository;

    @Value("${makerspace.email.username}")
    String from;

    @Value("${filePath}")
    String filePath;

         private FormalTeamRepository formalTeamRepository;
         private MiddleTermResultRepository middleTermResultRepository;
         private MiddleTermResult middleTermResult;
         public FormalTeam formalTeam;
         public TeamManagementService(FormalTeamRepository formalTeamRepository,MiddleTermResultRepository middleTermResultRepository)
         {
             this.formalTeamRepository=formalTeamRepository;
             this.middleTermResultRepository=middleTermResultRepository;
         }
    //获取团队进程
         public int findTeamById(int teamId)
         {
        formalTeam=this.formalTeamRepository.findAllByTeamId(teamId);
        return formalTeam.getAuditStep();
         }

    public MiddleTermResult getMiddleTermResult(int teamId)//获取团队中期评价
        {
        middleTermResult=this.middleTermResultRepository.findAllByTeamId(teamId);
        return middleTermResult;
        }
        /**
        @method:getTeamList
        @param:获取正式团队的列表
         */
        public TeamListVO GetTeamList(int page)
        {
            TeamListVO teamListVO=new TeamListVO();
            int num=10;//一页的最多团队个数
            ArrayList<FormalTeam> formalTeams=formalTeamRepository.findAll();
            int all=formalTeams.size();
            ArrayList<FormalTeam> formalTeamsend=new ArrayList<>(num);
            if(num*page>all)
            {
                for(int i=num*(page-1);i<all;i++)
                    formalTeamsend.add(formalTeams.get(i));
                teamListVO.setPart(all-num*(page-1));
            }
            else
            {
                for(int i=num*(page-1);i<num*page;i++)
                    formalTeamsend.add(formalTeams.get(i));
                teamListVO.setPart(num);
            }
            teamListVO.setAll(all);
            ArrayList<TeamMessage> teamMessages=new ArrayList<>(teamListVO.getPart());
            for(int i=0;i<teamListVO.getPart();i++)
            {
                TeamMessage teamMessage=new TeamMessage();
                teamMessage.setBelong_field(formalTeamsend.get(i).getBelongField());
                teamMessage.setProject_name(formalTeamsend.get(i).getProjectName());
                teamMessage.setProject_type(formalTeamsend.get(i).getProjectType());
                teamMessage.setRegister_date(formalTeamsend.get(i).getRegisterDate());
                teamMessage.setTeam_name(formalTeamsend.get(i).getTeamName());
                teamMessage.setTeamId(formalTeamsend.get(i).getTeamId());
                try {
                    teamMessage.setMessage(mesNumRepository.findAllByTeamId(teamMessage.getTeamId()).getMessageNum());
                }
                catch (NullPointerException e)
                {
                    teamMessage.setMessage(0);
                    MesNum mesNum=new MesNum();
                    mesNum.setMessageNum(0);
                    mesNum.setTeamId(teamMessage.getTeamId());
                    mesNumRepository.save(mesNum);
                }
                teamMessages.add(teamMessage);
            }
            teamListVO.setFormalTeams(teamMessages);
            return teamListVO;
        }
        /**
        @method:getTeamDetail
        获取团队详细信息
         */
        public TeamMore getTeamDetail(int team_id)
        {
            TeamMore teamMore=new TeamMore();
            FormalTeam formalTeam=formalTeamRepository.findAllByTeamId(team_id);
            teamMore.setBlong_field(formalTeam.getBelongField());
            teamMore.setWork_foundation(formalTeam.getWorkFoundation());
            teamMore.setTeam_name(formalTeam.getTeamName());
            teamMore.setTeam_id(formalTeam.getTeamId());
            teamMore.setRegister_date(formalTeam.getRegisterDate());
            teamMore.setProject_type(formalTeam.getProjectType());
            teamMore.setProject_director(formalTeam.getProjectDerector());
            teamMore.setProject_brief(formalTeam.getProjectBrief());
            teamMore.setPlan(formalTeam.getPlan());
            ArrayList<FormalMember> formalMembers = formalMemberRepository.findByTeamId(team_id);
            ArrayList<MemberMessage> memberMessages=new ArrayList<>(formalMembers.size());
            for(int i=0;i<formalMembers.size();i++)
            {
                MemberMessage memberMessage=new MemberMessage();
                memberMessage.setStudent_id(formalMembers.get(i).getStudentId());
                memberMessage.setStudent_name(formalMembers.get(i).getStudentName());
                try {
                    memberMessage.setSeat_no(seatRepository.findAllByStudentIdAndTeamId(memberMessage.getStudent_id(),team_id).getSeatNo());
                }catch (Exception e)
                {
                    memberMessage.setSeat_no(0);
                }
                try {
                    memberMessage.setRoom_no(seatRepository.findAllByStudentIdAndTeamId(memberMessage.getStudent_id(),team_id).getRoomNo());
                }
                catch (Exception e)
                {
                    memberMessage.setRoom_no(0);
                }
                try {
                    memberMessage.setAttendance_number(studentRepository.findOne(memberMessage.getStudent_id()).getAttendanceNumber());
                }
                catch (Exception e)
                {
                    memberMessage.setAttendance_number(0);
                }
                memberMessages.add(memberMessage);
            }
            teamMore.setMemberMessages(memberMessages);
            return teamMore;
        }
        /**
        @method changeMessageOfMember
        修改成员信息
         @return 修改成功为true 修改失败为false
         */
        public boolean changeMemberMessage(String student_id,int team_id,int room_no,int seat_no,int attendance_number)
        {
//            try {
                Student student=studentRepository.findOne(student_id);
                student.setAttendanceNumber(attendance_number);
                studentRepository.save(student);
                Seat seat=seatRepository.findAllByStudentIdAndTeamId(student_id,team_id);
                seat.setTeamId(null);
                seat.setStudentId(null);
                seatRepository.save(seat);
                Seat seat1=seatRepository.findAllByRoomNoAndSeatNo(room_no,seat_no);
                seat1.setStudentId(student_id);
                seat1.setTeamId(team_id);
                seatRepository.save(seat1);
//            }
//            catch (Exception e)
//            {
//                return false;
//            }
            return true;
        }
        /**
        @method:deleteMember
        删除成员信息
        @return 删除成功为true 删除不成功为false
         */
        public boolean deleteMember(String student_id,int team_id)
        {
            try {
                formalMemberRepository.deleteAllByStudentIdAndTeamId(student_id,team_id);
                seatRepository.deleteAllByStudentIdAndTeamId(student_id,team_id);
            }
            catch (Exception e)
            {
                return false;
            }
            return true;
        }
    /**
     * @method getTempTeamList
     * 获取临时团队的信息列表
     */
    public TempTeamList getTempTeamList(int page)
    {
        TempTeamList tempTeamList=new TempTeamList();
        int num=50;//单页存放的最多个数
        ArrayList<TempTeam> tempTeams=tempTeamRepository.findByAuditOpinionOrderBySubmitDateDesc("未处理");
        tempTeams.addAll(tempTeamRepository.findByAuditOpinionOrderBySubmitDateDesc("已拒绝"));
        tempTeams.addAll(tempTeamRepository.findByAuditOpinionOrderBySubmitDateDesc("已同意"));
        tempTeams.addAll(tempTeamRepository.findByAuditOpinionOrderBySubmitDateDesc("已入驻"));
        ArrayList<NumTempTeamDTO> tempTeamDTOS=new ArrayList<>(tempTeams.size());
        //准备所有的申请中和申请失败的团队信息
        for(int i=0;i<tempTeams.size();i++)
        {
            NumTempTeamDTO tempTeamDTO=new NumTempTeamDTO();
            tempTeamDTO.setAudit_option(tempTeams.get(i).getAuditOpinion());
            tempTeamDTO.setTeam_name(tempTeams.get(i).getTeamName());
            tempTeamDTO.setTeam_id(tempTeams.get(i).getTeamId());
            tempTeamDTO.setSubmit_date(tempTeams.get(i).getSubmitDate());
            tempTeamDTO.setCaptain_phone(tempMemberRepository.findCaptain(tempTeamDTO.getTeam_id()).getPhoneNumber());
            tempTeamDTO.setCaptain(tempMemberRepository.findCaptain(tempTeamDTO.getTeam_id()).getStudentName());
            tempTeamDTO.setSubmit_date(tempTeams.get(i).getSubmitDate());
            tempTeamDTO.setNumOfMember(Integer.toString(tempMemberRepository.findByTeamId(tempTeams.get(i).getTeamId()).size()));
            tempTeamDTOS.add(tempTeamDTO);
        }
        tempTeamList.setAll(tempTeamDTOS.size());
        ArrayList<NumTempTeamDTO> tempTeamDTOSend=new ArrayList<>();
        //整理单页的团队申请信息
        if(page*num>tempTeams.size())
        {
            for(int i=(page-1)*num;i<tempTeams.size();i++)
                tempTeamDTOSend.add(tempTeamDTOS.get(i));
            tempTeamList.setPart(tempTeamDTOSend.size());
            tempTeamList.setTempTeams(tempTeamDTOSend);
        }
        else
        {
            for(int i=(page-1)*num;i<page*num;i++)
                tempTeamDTOSend.add(tempTeamDTOS.get(i));
            tempTeamList.setPart(num);
            tempTeamList.setTempTeams(tempTeamDTOSend);
        }
        return tempTeamList;
    }

    /**
     * @method dealTempTeam
     * 处理申请中的团队，接受则把团队信息移到正式团队的表中
     * 拒绝则在团队信息上注明
     */
    public boolean dealTempTeam(String team_id,int choice,String reason) throws MessagingException {
//        try {
            if (choice == 1)//通过申请
            {
                MimeMessage mimeMessage=mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(tempMemberRepository.findByTeamIdAndOrderInTeam(team_id,(short)1).getEmail());
                mimeMessageHelper.setSubject("演武创客中心申请回复");
                HashMap<String,Object> map=new HashMap<>();
                map.put("teamName",tempTeamRepository.findByTeamId(team_id).getTeamName());
                map.put("confirmUrl",toConfirmUrl+team_id);
                map.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "emailtemplate/confirmEnter.vm", "utf-8", map);
                mimeMessageHelper.setText(text,true);
                mailSender.send(mimeMessage);

                TempTeam tempTeam=tempTeamRepository.findByTeamId(team_id);
                tempTeam.setAuditOpinion("已同意");
                tempTeamRepository.save(tempTeam);

            }
            else//拒绝申请
            {
                //修改字段为已拒绝
                tempTeamRepository.refuseTeam(team_id);
                Refusereason refusereason=new Refusereason();
                refusereason.setReason(reason);
                refusereason.setTeamId(team_id);
                refusereasonRepository.save(refusereason);
                //发送提示邮件
                MimeMessage mimeMessage=mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
                mimeMessageHelper.setFrom(from);
                mimeMessageHelper.setTo(tempMemberRepository.findByTeamIdAndOrderInTeam(team_id,(short)1).getEmail());
                mimeMessageHelper.setSubject("演武创客中心申请回复");
                HashMap<String,Object> map=new HashMap<>();
                map.put("teamName",tempTeamRepository.findByTeamId(team_id).getTeamName());
                map.put("date",new SimpleDateFormat("yyyy年MM月dd日").format(new java.util.Date()));

                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "emailtemplate/refuseTeamToEnter.vm", "utf-8", map);
                mimeMessageHelper.setText(text,true);
                mailSender.send(mimeMessage);
            }
//        }
//        catch (Exception e){
//            return false;
//        }
        return true;
    }

    /**
     * 返回临时团队的详细信息
     */
    public TempTeamMore getTempTeamMore(String team_id,HttpServletResponse httpServletResponse)
    {
        Cookie cookie=new Cookie("teamid",team_id);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        TempTeamMore tempTeamMore=new TempTeamMore();
        TempTeam tempTeam=tempTeamRepository.findByTeamId(team_id);
        //提取团队基础信息
        tempTeamMore.setAuditOpinion(tempTeam.getAuditOpinion());
        tempTeamMore.setWorkFoundation(tempTeam.getWorkFoundation());
        tempTeamMore.setTeamName(tempTeam.getTeamName());
        tempTeamMore.setTeamId(tempTeam.getTeamId());
        tempTeamMore.setSubmitDate(tempTeam.getSubmitDate());
        tempTeamMore.setProjectType(tempTeam.getProjectType());
        tempTeamMore.setProjectName(tempTeam.getProjectName());
        tempTeamMore.setProjectDirector(tempTeam.getProjectDirector());
        tempTeamMore.setProjectBrief(tempTeam.getProjectBrief());
        tempTeamMore.setPlan(tempTeam.getPlan());
        tempTeamMore.setEnterDueDate(tempTeam.getEnterDueDate());
        tempTeamMore.setBelongField(tempTeam.getBelongField());
        //获取团队成员信息列表
        ArrayList<TempMember> tempMembers=tempMemberRepository.findAllByTeamIdOrderByOrderInTeamAsc(team_id);
        tempTeamMore.setTempMembers(tempMembers);
        if(filePathRepository.findByTeamid(team_id)!=null) {
            tempTeamMore.setFilePath(filePathRepository.findByTeamid(team_id).getPath());
            tempTeamMore.setFileName(filePathRepository.findByTeamid(team_id).getPath().replace(filePath,""));
        }
        else
            tempTeamMore.setFilePath("no");
        return tempTeamMore;
    }
    /**
     * 修改团队信息
     */
    public boolean changeTeam(int team_id,String project_brief,String project_derector,String word_foundation,String plan)
    {
        try {
            FormalTeam formalTeam=formalTeamRepository.findAllByTeamId(team_id);
            formalTeam.setProjectBrief(project_brief);
            formalTeam.setProjectDerector(project_derector);
            formalTeam.setWorkFoundation(word_foundation);
            formalTeam.setPlan(plan);
            formalTeamRepository.save(formalTeam);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 提交团队申请
     */
    public Register submitTeam(NewTeam newTeam, HttpServletResponse httpServletResponse) throws MessagingException {
        Register register=new Register();
            TempTeam tempTeam = new TempTeam();
            tempTeam.setAuditOpinion("未处理");
            tempTeam.setWorkFoundation(newTeam.getWork_foundation());
            tempTeam.setTeamName(newTeam.getTeam_name());
            //团队id等于随机生成的4位数
            Random random=new Random();
            int teamid=random.nextInt(899999)+100000;
            while(!(formalTeamRepository.findAllByTeamId(teamid)==null&&tempTeamRepository.findByTeamId(Integer.toString(teamid))==null))
            {
                teamid=random.nextInt(899999)+100000;
            }
            String team_id = Integer.toString(teamid);

             Cookie cookie=new Cookie("teamid",team_id);
             cookie.setMaxAge(3600);
             cookie.setPath("/");
             httpServletResponse.addCookie(cookie);

             System.out.println("oj");
            tempTeam.setTeamId(team_id);
            ApplicationRound applicationRound = applicationRoundRepository.findAll().get(0);
            applicationRound.setTeamNumber((short)(applicationRound.getTeamNumber() + (short)1));
            applicationRoundRepository.save(applicationRound);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            tempTeam.setSubmitDate(sqlDate);
            tempTeam.setProjectType(newTeam.getProject_type());
            tempTeam.setProjectName(newTeam.getProject_name());
            tempTeam.setProjectDirector(newTeam.getProject_derector());
            tempTeam.setProjectBrief(newTeam.getProject_brief());
            tempTeam.setPlan(newTeam.getPlan());
            tempTeam.setBelongField(newTeam.getBelong_field());
            tempTeamRepository.save(tempTeam);
            //处理学生信息
            ArrayList<NewStudent> newStudents = newTeam.getNewStudents();
            for (int i = 0; i < newStudents.size(); i++) {
                TempMember tempMember = new TempMember();
                tempMember.setCollege(newStudents.get(i).getCollege());
                tempMember.setTeamId(team_id);
                tempMember.setStudentName(newStudents.get(i).getName());
                tempMember.setStudentId(newStudents.get(i).getStudent_id());
                tempMember.setPhoneNumber(newStudents.get(i).getPhone());
                tempMember.setOrderInTeam((short) (i+1));
                tempMember.setEmail(newStudents.get(i).getEmail());
                tempMember.setEducationBackground(newStudents.get(i).getEducation_background());
                tempMemberRepository.save(tempMember);
            }
            register.setAccount(team_id);
            register.setMessage("123456");
            register.setOk("true");

        //发送提示邮件
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(tempMemberRepository.findByTeamIdAndOrderInTeam(team_id,(short)1).getEmail());
        mimeMessageHelper.setSubject("演武创客中心申请提交确认");
        HashMap<String,Object> map=new HashMap<>();
        map.put("teamName",tempTeamRepository.findByTeamId(team_id).getTeamName());
        map.put("checkCode",team_id);

        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "emailtemplate/checkCodeEmail.vm", "utf-8", map);
        mimeMessageHelper.setText(text,true);
        mailSender.send(mimeMessage);
        return register;
    }

    /**
     * 再次提交团队申请
     * @param newTeam  团队的信息
     * @return
     */
    public Register submitTeamAgain(TeamAgainVO newTeam, String team_id)
    {
        System.out.println(team_id);
        Register register=new Register();
        TempTeam tempTeam=tempTeamRepository.findByTeamId(team_id);
        tempTeam.setBelongField(newTeam.getBelong_field());
        tempTeam.setAuditOpinion("未处理");
        tempTeam.setWorkFoundation(newTeam.getWork_foundation());
        tempTeam.setTeamName(newTeam.getTeam_name());
        tempTeam.setProjectType(newTeam.getProject_type());
        tempTeam.setPlan(newTeam.getPlan());
        tempTeam.setProjectDirector(newTeam.getProject_derector());
        tempTeam.setProjectName(newTeam.getProject_name());
        tempTeam.setProjectBrief(newTeam.getProject_brief());
        tempTeamRepository.save(tempTeam);
        try {
            refusereasonRepository.delete(team_id);
        }catch (Exception e)
        {
            System.out.println("拒绝理由出问题了");
        }
        tempMemberRepository.deleteAllByTeamId(team_id);
        //处理学生信息
        ArrayList<NewStudent> newStudents = newTeam.getNewStudents();
        for (int i = 0; i < newStudents.size(); i++) {
            TempMember tempMember = new TempMember();
            tempMember.setCollege(newStudents.get(i).getCollege());
            tempMember.setTeamId(team_id);
            tempMember.setStudentName(newStudents.get(i).getName());
            tempMember.setStudentId(newStudents.get(i).getStudent_id());
            tempMember.setPhoneNumber(newStudents.get(i).getPhone());
            tempMember.setOrderInTeam((short) (i+1));
            tempMember.setEmail(newStudents.get(i).getEmail());
            tempMember.setEducationBackground(newStudents.get(i).getEducation_background());
            tempMemberRepository.save(tempMember);
        }
        register.setOk("true");
        register.setMessage("123456");
        register.setAccount(team_id);
        return register;
    }
    /**
     * 判断是否在申请开放期间
     */
    public boolean isOpen()
    {
        if(applicationRoundRepository.findAll().size()==0)
            return false;
        else
        {
            if(applicationRoundRepository.findAll().get(0).getBeginDate().getTime()<System.currentTimeMillis()&&applicationRoundRepository.findAll().get(0).getEndDate().getTime()>System.currentTimeMillis())
                return true;
            else
                return false;
        }
    }


}
