package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.AdminEnterRepository;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;

/**
 * Created by status200 on 2017/9/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private  JavaMailSender mailSender;

    @Autowired
    AdminEnterRepository adminEnterRepository;

    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    ManageRegisterService manageRegisterService;



    @Test
    public void testSendEmail() throws Exception {
        manageRegisterService.refuseRegistration("51975");
//        AdminEnter adminEnter=new AdminEnter();
//        adminEnter.setAdminId(1);
//        adminEnter.setPasswordCode("123");
//        adminEnter.setAuthority(20);
//        adminEnter.setAdminAccount("123");
//        adminEnterRepository.save(adminEnter);
//        String nick="";
//        String from="24320162202894@stu.xmu.edu.cn";
//        String to="qwe1138318433@qq.com";
//        try {
//            nick=javax.mail.internet.MimeUtility.encodeText("24320162202894");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        Properties props = System.getProperties();
//        // 创建信件服务器
//        props.put("mail.smtp.host", "smtp.stu.xmu.edu.cn");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.transport.protocol", "smtp");
//        Authenticator a = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("24320162202894@stu.xmu.edu.cn", "Ren1138318433");
//            }
//        };
//        //创建Session实例
//        Session session = Session.getDefaultInstance(props, a);
//        //创建MimeMessage实例对象
//        MimeMessage msg=new MimeMessage(session);
//        //设置发件人
//        msg.setFrom(new InternetAddress(nick+" <"+from+">"));
//        //设置收信人
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//        //设置发送日期
//        msg.setSentDate(new Date());
//        //设置邮件主题
//        msg.setSubject("怎么会");
//        //设置邮件正文
//        msg.setText("这怎么会是");
//        mailSender.send(msg);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("24320162202894@stu.xmu.edu.cn");
//        message.setTo("qwe1138318433@qq.com");
//        message.setSubject("怎么会");
//        message.setText("这怎么会是");
//        mailSender.send(message);
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("teamName", "TPW");
//        params.put("teamId", "abcdefge");
//        params.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
//        params.put("rootUrl", rootUrl);
//
//        emailService.sendTemplateEmail("emailtemplate/notifyTeamToConfirmEnter.vm", params, "演武创客空间入驻确认", "qwe1138318433@qq.com");

    }


    @Test
    public void temp() throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("24320162202894@stu.xmu.edu.cn");
        mimeMessageHelper.setTo("qwe1138318433@qq.com");
        mimeMessageHelper.setSubject("演武创客中心申请回复");
        HashMap<String,Object> map=new HashMap<>();
        map.put("teamName","kdsjk");
        map.put("urlOfLogin","http://localhost:8082/#/Login");

        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "emailtemplate/notifyTeamToConfirmEnter.vm", "utf-8", map);
        mimeMessageHelper.setText(text,true);
        mailSender.send(mimeMessage);
    }
}
