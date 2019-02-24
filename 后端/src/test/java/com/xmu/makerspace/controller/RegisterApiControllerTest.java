package com.xmu.makerspace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmu.makerspace.model.RegisterSubmitDTO;
import com.xmu.makerspace.model.TempMemberDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.ContentType;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by status200 on 2017/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testSubmit() throws Exception {
        RegisterSubmitDTO data = new RegisterSubmitDTO();

        data.setBelongField("金融业");
        data.setPlan("项目规划");
        data.setProjectBrief("项目描述");
        data.setProjectDirector("主营业务");
        data.setProjectName("项目名");
        data.setTeamId(UUID.randomUUID().toString());
        data.setTeamName("TPW");
        data.setWorkFoundation("前期基础");
        data.setProjectType("自选");

        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList<TempMemberDTO> members = new ArrayList<>();
        members.add(new TempMemberDTO("软件学院", "本科", "358941501@qq.com", 1, "24320152202752", "黄一鑫", data.getTeamId(),
                "15392035361"));
        members.add(new TempMemberDTO("软件学院", "本科", "529930262@qq.com", 2, "24320152202755", "江子攸", data.getTeamId(),
                "15959237237"));
        data.setMembers(members);

        mvc.perform(
                post("/api/register/submit")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(data)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
