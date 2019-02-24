package com.xmu.makerspace.config;

import org.apache.tomcat.jni.SSL;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 发送邮件配置
 *
 * Created by status200 on 2017/8/10.
 */
@Configuration
//@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Value("${makerspace.email.username}")
    private String username;

    @Value("${makerspace.email.password}")
    private String password;

    @Value("${makerspace.email.host}")
    private String host;
//
//    @Value("${makerspace.email.port}")
//    private int port;

    @Bean
    public JavaMailSenderImpl mailSenderConfig() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setDefaultEncoding("utf-8");
        //配置邮箱用户
        mailSender.setUsername(username);
        //配置邮箱密码
        mailSender.setPassword(password);
        //配置邮箱服务器，这里是为了发邮件，所以要配置的是支持smtp协议的邮箱服务器，可以在邮箱客户端寻找服务器地址信息
        mailSender.setHost(host);
//        mailSender.setPort(25);

        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.timeout", "25000");
        javaMailProperties.setProperty("mail.transport.protocol", "smtp");
        javaMailProperties.setProperty("mail.smtp.ssl.enable", "false");

//        javaMailProperties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//        javaMailProperties.setProperty("mail.smtp.socketFactory.fallback", "false");
//        //邮箱发送服务器端口,这里设置为465端口
//        javaMailProperties.setProperty("mail.smtp.port", "110");
//        javaMailProperties.setProperty("mail.smtp.socketFactory.port", "995");

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    @Bean
    public VelocityEngine velocityEngineConfig() {
        VelocityEngine velocityEngine = new VelocityEngine();

//        Properties properties = new Properties();
//        properties.setProperty("resource.loader", "class");
//        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader" +
//                ".ClasspathResourceLoader");

        velocityEngine.addProperty("resource.loader", "class");
        velocityEngine.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader" +
                ".ClasspathResourceLoader");

        return velocityEngine;
    }

}
