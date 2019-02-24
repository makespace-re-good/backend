package com.xmu.makerspace.controller;

import com.xmu.makerspace.domain.TempTeam;
import com.xmu.makerspace.model.ManageRegisterVO;
import com.xmu.makerspace.service.ManageRegisterService;
import com.xmu.makerspace.service.TempTeamFileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NonNls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by status200 on 2017/8/23.
 */
@RestController
@RequestMapping("api/manage-register")
public class ManageRegisterApiController {

    @NonNls
    private static Log log = LogFactory.getLog(ManageRegisterApiController.class);

    private final ManageRegisterService manageRegisterService;

    private final TempTeamFileService tempTeamFileService;


    @Autowired
    public ManageRegisterApiController(ManageRegisterService manageRegisterService,
                                       TempTeamFileService tempTeamFileService) {
        this.manageRegisterService = manageRegisterService;
        this.tempTeamFileService = tempTeamFileService;
    }

    @GetMapping("get-all-registration")
    public List<ManageRegisterVO> getAllRegistration() {
        return manageRegisterService.getAllRegistration();
    }

    @PostMapping("agree-registration")
    public void agreeRegistration(@RequestParam("teamId") String teamId) throws MessagingException {

        manageRegisterService.agreeRegistration(teamId);

    }

    @PostMapping("refuse-registration")
    public void refuseRegistration(@RequestParam("teamId") String teamId) throws MessagingException {
        manageRegisterService.refuseRegistration(teamId);
    }

    @GetMapping("download-file/{teamId:[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}}/{fileName}/")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("teamId") String teamId, @PathVariable("fileName")
            String fileName) {

        try {
            byte[] fileByteArray = tempTeamFileService.getFileEntity(teamId, fileName);

            // 设置下载响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(String.format("fail to download temp team %s file %s", teamId, fileName));
            e.printStackTrace();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("download-zip/{teamId:[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}}/")
    public ResponseEntity<byte[]> downloadZip(@PathVariable("teamId") String teamId) {

        TempTeam tempTeam = manageRegisterService.findTempTeamByTeamId(teamId);
        String fileName = String.format("%s.zip", tempTeam.getTeamName());

        try {
            byte[] fileByteArray = tempTeamFileService.getZipEntity(tempTeam.getTeamId(), tempTeam.getTeamName());
            // 设置下载响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error(String.format("fail to download temp team %s zip", tempTeam.getTeamId()));
            e.printStackTrace();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
