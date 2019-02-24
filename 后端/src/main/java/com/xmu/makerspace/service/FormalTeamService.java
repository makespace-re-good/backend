package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.*;
import com.xmu.makerspace.domain.*;
import com.xmu.makerspace.model.SeatDTO;
import com.xmu.makerspace.model.SeatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 对正式团队的一些操作
 */
@Service
public class FormalTeamService {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    FormalMemberRepository formalMemberRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    MesNumRepository mesNumRepository;
    @Autowired
    FormalTeamRepository formalTeamRepository;
    @Autowired
    TeamEnterRepository teamEnterRepository;
    /**
     * 分配座位
     */
    public boolean chooseSeat(String student_id,int team_id,int root_no,int seat_no)
    {
        try{
            Seat seat=seatRepository.findAllByRoomNoAndSeatNo(root_no,seat_no);
            seat.setStudentId(student_id);
            seat.setTeamId(team_id);
            seatRepository.save(seat);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
    /**
     * 获取没人的座位信息的列表
     */
    public ArrayList<SeatMessage> getSeatList()
    {
        ArrayList<Seat> seats=seatRepository.findAllByStudentIdIsNull();
        ArrayList<SeatMessage> seatMessages=new ArrayList<>();
        for(int i=0;i<seats.size();i++)
        {
            SeatMessage seatMessage=new SeatMessage();
            seatMessage.setRoot_no(seats.get(i).getRoomNo());
            seatMessage.setSeat_no(seats.get(i).getSeatNo());
            seatMessages.add(seatMessage);
        }
        return seatMessages;
    }
    /**
     * 获取没有分配位置的人员列表
     */
    public ArrayList<FormalMember> getMemberWithoutSeat(int team_id)
    {
        ArrayList<FormalMember> formalMembers=formalMemberRepository.findByTeamId(team_id);
        ArrayList<FormalMember> formalMembersEnd=new ArrayList<>();
        for(int i=0;i<formalMembers.size();i++)
        {
//            try{
//              seatRepository.findAllByStudentIdAndTeamId(formalMembers.get(i).getStudentId(),formalMembers.get(i).getTeamId());
//            }
//            catch (Exception e)
//            {
//                formalMembersEnd.add(formalMembers.get(i));
//                continue;
//            }
            if(seatRepository.findAllByStudentIdAndTeamId(formalMembers.get(i).getStudentId(),formalMembers.get(i).getTeamId())==null)
                formalMembersEnd.add(formalMembers.get(i));
        }
        return formalMembersEnd;
    }
    /**
     * 获取未录入考勤号的人员列表
     */
    public ArrayList<FormalMember> getMemberWithoutAtt(int team_id)
    {
        ArrayList<Student> students=studentRepository.findAll();
        ArrayList<Student> studentsend=new ArrayList<>();
        for(int i=0;i<students.size();i++)
        {
            if(students.get(i).getAttendanceNumber()==null)
                studentsend.add(students.get(i));
        }
        ArrayList<FormalMember> formalMembers=new ArrayList<>();
        for(int i=0;i<studentsend.size();i++)
        {
            formalMembers.add(formalMemberRepository.findAllByStudentIdAndTeamId(studentsend.get(i).getStudentId(),team_id));
        }
        return formalMembers;
    }

    /**
     * 录入考勤号
     */
    public boolean chooseAtt(String student_id,int att)
    {
        try {
            Student student = studentRepository.findOne(student_id);
            student.setAttendanceNumber(att);
            studentRepository.save(student);
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 提出反馈申请
     */
    public boolean submitRequest(String team_id,String title,String content,int type) {
 //       try {
            String request_id = team_id + Integer.toString(requestRepository.findAllByTeamId(team_id).size());
            Request request = new Request();
            request.setContent(content);
            request.setRequestId(request_id);
            request.setTeamId(team_id);
            request.setType(type);
            request.setTitle(title);
            requestRepository.save(request);
            MesNum mesNum = mesNumRepository.findAllByTeamId(Integer.parseInt(team_id));
            mesNum.setMessageNum(mesNum.getMessageNum() + 1);
            mesNumRepository.save(mesNum);
//        } catch (Exception e)
//        {
//            return false;
//        }
        return true;
    }

    /**
     * 获取反馈申请
     */
    public ArrayList<Request> getRequestList(String team_id)
    {
        ArrayList<Request> requests=requestRepository.findAllByTeamId(team_id);
        return requests;
    }
    /**
     * 获取所有的反馈
     */
    public ArrayList<Request> getAllRequestList()
    {
        return requestRepository.findAll();
    }

    /**
     * 处理反馈
     */
    public boolean dealRequest(String request_id,String team_id)
    {
        try {
                requestRepository.delete(requestRepository.findOne(request_id));
                MesNum mesNum = mesNumRepository.findAllByTeamId(Integer.parseInt(team_id));
                mesNum.setMessageNum(mesNum.getMessageNum()-1);
                mesNumRepository.save(mesNum);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
    /**
     * 获取需要录入座位号的队伍数量
     */
    public int numOfTeamNeedSeat()
    {
        int num=0;
        List<FormalTeam> formalTeams=formalTeamRepository.findAll();
        for(int i=0;i<formalTeams.size();i++)
        {
            if(getMemberWithoutSeat(formalTeams.get(i).getTeamId()).size()!=0)
                num++;
        }
        return num;
    }
    /**
     * 获取需要录入考勤号的队伍的数量
     */
    public int numOfTeamNeedAtt()
    {
        int num=0;
        List<FormalTeam> formalTeams=formalTeamRepository.findAll();
        for(int i=0;i<formalTeams.size();i++)
        {
            if(getMemberWithoutAtt(formalTeams.get(i).getTeamId()).size()!=0)
                num++;
        }
        return num;
    }

    /**
     * 团队登录
     */
    public boolean login(int account,String password)
    {
        if(teamEnterRepository.findByTeamId(account)!=null&&teamEnterRepository.findByTeamId(account).getPassword().equals(password))
        {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TeamEnter teamEnter=teamEnterRepository.findByTeamId(account);
            teamEnter.setLastEnterTime(Timestamp.valueOf(simpleDateFormat.format(System.currentTimeMillis())));
            teamEnterRepository.save(teamEnter);
            return true;
        }
        else
            return false;
    }

    /**
     * 根据房间号获取座位信息
     */
    public ArrayList<SeatDTO> getSeatMes(String room_no,String team_id)
    {
        //System.out.println(room_no);
        ArrayList<SeatDTO> seatDTOS=new ArrayList<>();
        List<Seat> seats=seatRepository.findByRoomNo(Integer.parseInt(room_no));
        for(int i=0;i<seats.size();i++)
        {
            SeatDTO seatDTO=new SeatDTO();
            seatDTO.setSeatno(Integer.toString(seats.get(i).getSeatNo()));
            if(seats.get(i).getStudentId()==null||seats.get(i).getStudentId().equals(""))
                seatDTO.setOk("true");

            else
            {
                if(team_id!=null&&seats.get(i).getTeamId().toString().equals(team_id)) {
                    seatDTO.setTeamname(formalTeamRepository.findAllByTeamId(seats.get(i).getTeamId()).getTeamName());
                    seatDTO.setName(formalMemberRepository.findAllByStudentIdAndTeamId(seats.get(i).getStudentId(),Integer.parseInt(team_id)).getStudentName());
                    seatDTO.setOk("yes");
                    System.out.println(seats.get(i).getTeamId().toString());
                    System.out.println(team_id);
                }
                else
                {
                    seatDTO.setTeamname(formalTeamRepository.findAllByTeamId(seats.get(i).getTeamId()).getTeamName());
                    seatDTO.setName(formalMemberRepository.findAllByStudentIdAndTeamId(seats.get(i).getStudentId(),seats.get(i).getTeamId()).getStudentName());
                    seatDTO.setOk("false");
                    System.out.println(seats.get(i).getTeamId().toString());
                    System.out.println(team_id);
                }
            }
            seatDTOS.add(seatDTO);
        }
        return seatDTOS;
    }

    /**
     * 考勤号查重
     * @param attendance_number
     * @return
     */
    public boolean checkAttendanceRepeat(int attendance_number)
    {
        System.out.println(attendance_number);
        if(studentRepository.findByAttendanceNumber(attendance_number)==null)
            return true;
        else
            return false;
    }

    /**
     * 修改密码
     */
    public boolean changePassword(String teamid,String oldPassword,String newPassword)
    {
        if(teamEnterRepository.findByTeamId(Integer.parseInt(teamid)).getPassword().equals(oldPassword))
        {
            TeamEnter teamEnter=teamEnterRepository.findByTeamId(Integer.parseInt(teamid));
            teamEnter.setPassword(newPassword);
            teamEnterRepository.save(teamEnter);
            return true;
        }
        return false;
    }
}
