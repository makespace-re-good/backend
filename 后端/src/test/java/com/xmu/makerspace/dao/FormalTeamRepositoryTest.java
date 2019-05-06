package com.xmu.makerspace.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormalTeamRepositoryTest {
    @Autowired
    private FormalTeamRepository formalTeamRepository;

    @Test
    public void testDeleteTeam() throws Exception{
       System.out.println(formalTeamRepository.deleteTeam(123,2018092));
    }
}
