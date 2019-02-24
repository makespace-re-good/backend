package com.xmu.makerspace.controller;

import com.xmu.makerspace.dao.EmailTemplateRepository;
import com.xmu.makerspace.dao.FormalMemberRepository;
import com.xmu.makerspace.domain.EmailTemplate;
import com.xmu.makerspace.model.email.EditTemplateDTO;
import com.xmu.makerspace.model.email.NewTemplateDTO;
import com.xmu.makerspace.model.email.SendEmailDTO;
import com.xmu.makerspace.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("sendTemplateEmail")
public class SendEmailControllor {
    @Autowired
    SendEmailService sendEmailService;
    @Autowired
    EmailTemplateRepository emailTemplateRepository;
    @Autowired
    FormalMemberRepository formalMemberRepository;

    //获取所有的邮件模板
    @RequestMapping("getAllTemplate")
    public ArrayList<EmailTemplate> getAllTemplate() {
        return sendEmailService.getAllTemplate();
    }

    /**
     * 发送邮件到团队负责人
     *
     * @return
     */
    @PostMapping("sendSimpleEmailToTeam")
    public boolean sendSimpleEmail(@RequestBody SendEmailDTO sendEmailDTO) {
        return sendEmailService.sendSimpleEmail(sendEmailDTO.getTopic(), sendEmailDTO.getContent(), sendEmailDTO.getRecipients());
    }

    /**
     * 新建一个模板
     *
     * @param newTemplateDTO
     * @return
     */
    @PostMapping("newTemplate")
    public boolean newTemplate(@RequestBody NewTemplateDTO newTemplateDTO) {
        return sendEmailService.newTemplate(newTemplateDTO);
    }

    /**
     * 发送邮件到个人    测试阶段
     * @return
     */
//    @PostMapping("sendSimpleEmailByMember")
//    public boolean sendSimpleEmailByMember(@RequestParam int emailTemplateId, @RequestParam ArrayList<String> recipient)
//    {
//        EmailTemplate emailtemplate=emailTemplateRepository.findOne(emailTemplateId);
//        return sendEmailService.sendSimpleEmail(emailtemplate.getTopic(),emailtemplate.getContent(),recipient);
//    }

    /**
     * 修改模板
     * @param editTemplateDTO
     * @return
     */
    @PostMapping("editTemplate")
    public boolean  editTemplate(@RequestBody EditTemplateDTO editTemplateDTO)
    {
        return sendEmailService.editTemplate(editTemplateDTO);
    }

    @GetMapping("deleteTemplate")
    public boolean deleteTemplate(int id)
    {
        return sendEmailService.deleteTemplate(id);
    }


}
