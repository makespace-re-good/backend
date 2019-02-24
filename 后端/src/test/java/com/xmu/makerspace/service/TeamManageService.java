package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.FormalTeamRepository;
import com.xmu.makerspace.dao.SeatRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.domain.FormalTeam;
import com.xmu.makerspace.domain.Seat;
import com.xmu.makerspace.domain.TempTeam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamManageService {
    @Autowired
    TeamManagementService teamManagementService;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    FormalTeamRepository formalTeamRepository;
    @Autowired
    TempTeamRepository tempTeamRepository;
    @Autowired
    FormalTeamService formalTeamService;

    @Test
    public void GetTeamListTest()
    {
        System.out.println(teamManagementService.GetTeamList(1).getPart());
    }
    @Test
    public void GetTeamMore()
    {
        System.out.println(teamManagementService.getTeamDetail(124).getProject_brief());
    }
//    @Test
//    public void changeMember()
//    {
//        System.out.println(teamManagementService.changeMemberMessage("1",5,555,666));
//    }
    @Test
    public void deleteMember()
    {
        System.out.println(teamManagementService.deleteMember("1",123));
    }
    @Test
    public void getList()
    {
        System.out.println(teamManagementService.getTempTeamList(1).getAll());
//        java.util.Date utilDate=new java.util.Date();
//        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
//        System.out.println(sqlDate.toString());
    }
    @Test
    public void dealTest() throws MessagingException {
//        TempTeam tempTeam=tempTeamRepository.findByTeamId("951");
//        System.out.println(tempTeam.getTeamName());
        teamManagementService.dealTempTeam("1717",1," ");
    }
    @Test
    public void TempTeamTest()
    {
//        System.out.println(teamManagementService.getTempTeamMore("777").getTempMembers().size());

//        seatRepository.removeRecord("243",123);
       //seatRepository.changeSeat(1,5,"243",123);
        //formalTeamRepository.changeTeam("123456789","123456789","123456789","123456789",123);
  //
        formalTeamService.chooseSeat("2894",2018724,1,6);
    }
    @Test
    public void testChange()
    {
        teamManagementService.changeMemberMessage("2894",2018724,211,1,6789);
    }
//    @Test
//    public void testEmail()
//    {
//        teamManagementService.email("qwe1138318433@qq.com");
//    }

}
