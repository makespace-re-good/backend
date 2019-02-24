package com.xmu.makerspace.service;

import com.xmu.makerspace.model.attendance.AttendanceNumberDTO;
import com.xmu.makerspace.model.attendance.AttendanceNumberVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamInServiceTest {

    @Autowired
    private TeamInService teamInService;
    
    @Test
    public void testConfirmEnter() throws Exception {
        teamInService.confirmEnter("12345");
    }

    @Test
    public void testGetRoomStatistic() throws Exception {
        System.out.println(teamInService.getRoomStatistic());
    }

    @Test
    public void testAttendanceNumberSubmit() throws Exception {

        List<AttendanceNumberDTO> dtos = new ArrayList<>();

        AttendanceNumberDTO dto = new AttendanceNumberDTO();
        dto.setStudentId("24320152202752");
        dto.setAttendanceNumber(245);

        dtos.add(dto);

        teamInService.attendanceNumberSubmit(dtos);
    }
}
