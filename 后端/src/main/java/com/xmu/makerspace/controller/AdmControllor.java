package com.xmu.makerspace.controller;

import com.xmu.makerspace.service.AdmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin
@RequestMapping("/adm")
@RestController
public class AdmControllor {
    @Autowired
    AdmService admService;
    @GetMapping("/login")
    public int AdmLogIn(String username,String password)
    {
        String date=(new Date(System.currentTimeMillis())).toString();
        return admService.admLogin(username,password,date);
    }
    @RequestMapping("edit-application-round")
    public boolean editApplicationRound(String beginDate,String endDate,int termId)
    {
        return admService.editApplicationRound(beginDate,endDate,termId);
    }
}
