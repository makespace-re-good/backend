package com.xmu.makerspace.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by status200 on 2017/9/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Test
    public void testFindDistinctRoomNo() throws Exception {
        System.out.println(seatRepository.findDistinctRoomNo());
    }

    @Test
    public void testFindSimpleMemberOfSeatByStudentId() throws Exception {

        System.out.println(seatRepository.findSimpleMemberOfSeatByStudentId("24320152202752"));
    }
}
