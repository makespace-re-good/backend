package com.xmu.makerspace.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送服务
 *
 * Created by status200 on 2017/8/10.
 */
@Service
//@PropertySource("classpath:mail.properties")
public class EmailService {

    // 发件人的邮箱账号
    @Value("${makerspace.email.username}")
    private String from;

    private final JavaMailSender mailSender;

    private final VelocityEngine velocityEngine;

    @Autowired
    public EmailService(JavaMailSenderImpl mailSender, VelocityEngine velocityEngine) {
        this.mailSender = mailSender;
        this.velocityEngine = velocityEngine;
    }


    /**
     * 通用的邮件模板接口.
     * @param template    使用的模板路径
     * @param params      往模板里边填充的参数,采用键值对的方式,传入的Map参数为(参数名，参数值(可以为对象))
     * @param subject     邮件主题
     * @param to          收件人邮箱地址,可以有多个收件人
     * @param attachments 附件,传入的Map参数为(附件名(包含扩展名),附件路径)
     */
    public boolean sendTemplateEmail(String template,
                                      Map<String, Object> params,
                                      String subject,
                                      List<String> to,
                                      Map<String, String> attachments) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        try {

            // 设置发件人
            helper.setFrom(from);

            // 设置收件人
            to.forEach(v -> {
                try {
                    helper.setTo(v);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            // 设置标题
            helper.setSubject(subject);

            // 设置文字内容
            String  text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template,"utf-8", params);

            helper.setText(text,true);

            // 如果有附件,设置附件
            if (attachments != null) {
                attachments.forEach((k, v) -> {
                    try {
                        helper.addAttachment(k,new ClassPathResource(v));
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
            }

            // 发送邮件
            mailSender.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 邮件只发送给一个收件人，需要附件
     */
    public boolean sendTemplateEmail(String template,
                                      Map<String, Object> params,
                                      String subject,
                                      String to,
                                      Map<String, String> attachments) throws MessagingException {
        ArrayList<String> target = new ArrayList<>();
        target.add(to);

        return sendTemplateEmail(template,params,subject,target,attachments);
    }

    /**
     * 发送给一个收件人，不需要附件
     */
    public boolean sendTemplateEmail(String template,
                                      Map<String, Object> params,
                                      String subject,
                                      String to) throws MessagingException {
        ArrayList<String> target = new ArrayList<>();
        target.add(to);

        return sendTemplateEmail(template,params,subject,target,null);
    }
}
