package com.xmu.makerspace.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by status200 on 2017/8/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ManageRegisterApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllRegistration() throws Exception {
        mvc.perform(
                get("/api/manage-register/get-all-registration")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAgreeRegistration() throws Exception {
        mvc.perform(
                post("/api/manage-register/agree-registration")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("teamId", "aa94c311-e04b-42bb-a65a-56338d3d9d7d"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void testRefuseRegistration() throws Exception {
        mvc.perform(
                post("/api/manage-register/refuse-registration")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("teamId", "8cf9af68-c25e-4624-833b-31088e6a35ce"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
