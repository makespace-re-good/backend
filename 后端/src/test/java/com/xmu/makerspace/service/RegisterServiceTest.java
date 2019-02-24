package com.xmu.makerspace.service;

import com.xmu.makerspace.model.RegisterSubmitDTO;
import com.xmu.makerspace.model.TempMemberDTO;
import com.xmu.makerspace.model.UpdateRegisterVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NonNls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by status200 on 2017/8/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {

    @NonNls
    private static final Log log = LogFactory.getLog(RegisterServiceTest.class);

    @Autowired
    private RegisterService registerService;

    @Test
    public void testSubmit() {
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

        ArrayList<TempMemberDTO> members = new ArrayList<>();
        members.add(new TempMemberDTO("软件学院", "本科", "358941501@qq.com", 1, "24320152202752", "黄一鑫", data.getTeamId(),
                "15392035361"));
        members.add(new TempMemberDTO("软件学院", "本科", "529930262@qq.com", 2, "24320152202755", "江子攸", data.getTeamId(),
                "15959237237"));
        data.setMembers(members);

        log.info("submit data is " + data);

        registerService.submit(data);

    }

    @Test
    public void testValidateTeamName() {
        System.out.println(registerService.validateTeamName("TPW", "94ab790b-653d-4cd1-ab10-a87206e56515"));
    }



}
