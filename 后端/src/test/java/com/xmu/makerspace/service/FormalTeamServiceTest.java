package com.xmu.makerspace.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormalTeamServiceTest {
    @Autowired
    FormalTeamService formalTeamService;
    @Test
    public void testSeat()
    {
        formalTeamService.chooseSeat("2",1717,1,5);
    }
}
