package com.xmu.makerspace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmu.makerspace.model.NewApplicationRoundDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by status200 on 2017/8/30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BackendIndexApiControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetStatistic() throws Exception {
        mvc.perform(
                get("/api/backend-index/get-statistic")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testCreateNewApplicationRound() throws Exception {
        Map<String, Object> dto = new HashMap<>();
        dto.put("termId", 201708);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.AUGUST, 30);
        dto.put("beginDate", calendar.getTime());
        calendar.set(2017, Calendar.SEPTEMBER, 5);
        dto.put("endDate", calendar.getTime());

        mvc.perform(
                post("/api/backend-index/create-new-application-round")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCloseCurrentApplicationRound() throws Exception {
        Map<String, String> dto = new HashMap<>();
        dto.put("termId", "201708");

        mvc.perform(
                post("/api/backend-index/close-current-application-round")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .characterEncoding("utf-8")
                        .content(mapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testValidateTermId() throws Exception {
        mvc.perform(
                get("/api/backend-index/validate-term-id")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("termId", "201708")
                        .characterEncoding("utf-8")
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
