package com.xmu.makerspace.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmu.makerspace.Application;
import com.xmu.makerspace.model.NewApplicationRoundDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by status200 on 2017/8/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendIndexServiceTest {

    @Autowired
    private BackendIndexService backendIndexService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetStatistic() throws Exception {
        System.out.println(mapper.writeValueAsString(backendIndexService.getStatistic()));
    }

//    @Test
//    public void testCreateNewApplicationRound() throws Exception {
//
//        NewApplicationRoundDTO dto = new NewApplicationRoundDTO();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2017,Calendar.AUGUST,30);
//        dto.setBeginDate(new Date(calendar.getTime().getTime()));
//        calendar.set(2017,Calendar.SEPTEMBER,5);
//        dto.setEndDate(new Date(calendar.getTime().getTime()));
//        dto.setTermId(201708);
//
//        backendIndexService.createNewApplicationRound(dto);
//    }

    @Test
    public void testCloseCurrentApplication() throws Exception {

        backendIndexService.closeCurrentApplicationRound( );
    }

    @Test
    public void testValidateTermId() throws Exception {
        System.out.println(backendIndexService.validateTermId(201709));
    }
}
