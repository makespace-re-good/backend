package com.xmu.makerspace.controller;

import com.xmu.makerspace.model.RegisterSubmitDTO;
import com.xmu.makerspace.service.EmailService;
import com.xmu.makerspace.service.RegisterService;
import com.xmu.makerspace.service.TempTeamFileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NonNls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by status200 on 2017/8/5.
 */
@RestController
@RequestMapping("/api/register")
public class RegisterApiController {

    @NonNls
    private static Log log = LogFactory.getLog(RegisterApiController.class);

    private final RegisterService registerService;

    private final TempTeamFileService tempTeamFileService;

    private final EmailService emailService;

    @Autowired
    public RegisterApiController(RegisterService registerService, TempTeamFileService tempTeamFileService,
                                 EmailService emailService) {
        this.registerService = registerService;
        this.tempTeamFileService = tempTeamFileService;
        this.emailService = emailService;
    }


    @PostMapping("validate-team-name")
    public Map<String, Object> validateTeamName(@RequestBody Map<String, Object> data) {

        String teamName = (String) data.get("teamName");
        String teamId = (String) data.get("teamId");

        log.info("teamName is : " + teamName + " and teamId is : " + teamId);

        HashMap<String, Object> respData = new HashMap<>();
//        respData.put("valid",true);
        respData.put("valid", registerService.validateTeamName(teamName, teamId));

        return respData;
    }

    @PostMapping("upload-file")
    public void uploadFile(MultipartFile file, @RequestParam("teamId") String teamId) {
        log.info(String.format("temp team \"%s\" uploads a file \"%s\", size %d Byte", teamId, file
                .getOriginalFilename(), file.getSize()));

        try {
            registerService.saveFile(file, teamId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.format("error occurred on attempting to save temp team \"%s\"'s uploaded file \"%s\"",
                    teamId, file.getOriginalFilename()));
        }

    }

    @PostMapping("submit")
    public void submit(@RequestBody RegisterSubmitDTO data) {
        log.info("submit data " + data);

        registerService.submit(data);

        // 获得负责人的邮箱
        String leaderEmail = data.getMembers().stream().filter(e -> e.getOrderInTeam() == 1).collect(Collectors
                .toList()).get(0).getEmail();

        HashMap<String, Object> params = new HashMap<>();
        params.put("teamName", data.getTeamName());
        params.put("checkCode", data.getTeamId());
        params.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));

        try {
            emailService.sendTemplateEmail("emailtemplate/checkCodeEmail.vm", params, "创客空间团队验证码", leaderEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error(String.format("error occurred on attempting to send check-code email to temp team \"%s\", team " +
                    "id %s", data.getTeamName(), data.getTeamId()));
        }
    }

    @PostMapping("delete-file")
    public void deleteFile(@RequestBody Map<String, Object> data) {
        String teamId = (String) data.get("teamId");
        String fileName = (String) data.get("fileName");

        log.info(String.format("temp team \"%s\" deletes a file \"%s\"", teamId, fileName));

        registerService.deleteFile(fileName, teamId);

    }


}
