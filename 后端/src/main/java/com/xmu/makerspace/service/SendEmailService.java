package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.EmailTemplateRepository;
import com.xmu.makerspace.dao.FormalMemberRepository;
import com.xmu.makerspace.dao.FormalTeamRepository;
import com.xmu.makerspace.domain.EmailTemplate;
import com.xmu.makerspace.model.email.EditTemplateDTO;
import com.xmu.makerspace.model.email.NewTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class SendEmailService {
    @Value("${makerspace.email.username}")
    String username;
    @Autowired
    EmailTemplateRepository emailTemplateRepository;
    @Autowired
    JavaMailSender  mailSender;
    @Autowired
    FormalTeamRepository formalTeamRepository;
    @Autowired
    FormalMemberRepository formalMemberRepository;
    //获取所有邮件模板
    public ArrayList<EmailTemplate> getAllTemplate()
    {
        return emailTemplateRepository.findAll();
    }
    //发送简单的邮件给团队，即发给团队的队长
    public boolean sendSimpleEmail(String topic,String content,ArrayList<Integer> recepients)
    {
//        try {
        System.out.println(recepients.size());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setSubject(topic);
            for (int i = 0; i < recepients.size(); i++) {
                System.out.println(recepients.get(i));
                String newContent=content.replace("{TeamName}",formalTeamRepository.findAllByTeamId(recepients.get(i)).getTeamName());
                newContent=newContent.replace("{DateTime}",new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
                message.setText(newContent);
                message.setTo(formalMemberRepository.findByTeamIdAndOrderInTeam(recepients.get(i),(short)1).getEmail());
                mailSender.send(message);
            }
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return false;
//        }
        return true;
    }

    //新建一个邮件模板
    @Transactional
    public boolean newTemplate(NewTemplateDTO newTemplateDTO)
    {
        try {

            EmailTemplate emailTemplate=new EmailTemplate();
            emailTemplate.setContent(newTemplateDTO.getContent());
            emailTemplate.setTopic(newTemplateDTO.getTopic());
            emailTemplate.setCouldDelete(newTemplateDTO.getCouldDelete());
            emailTemplateRepository.save(emailTemplate);
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    //编辑邮件模板
    public boolean editTemplate(EditTemplateDTO editTemplateDTO)
    {
        try {
            EmailTemplate emailTemplate=emailTemplateRepository.findOne(editTemplateDTO.getId());
            emailTemplate.setTopic(editTemplateDTO.getTopic());
            emailTemplate.setContent(editTemplateDTO.getContent());
            emailTemplateRepository.save(emailTemplate);
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean deleteTemplate(int id){
        try{
            emailTemplateRepository.delete(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
