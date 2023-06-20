package com.graduate.touslestemp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/*
* @File:  MailConfig.java com.graduate.touslestemp.config
*
* @Author: TamNLT
* @Since: 20/6/2023 11:08 PM
* @Last update: 20/6/2023
*
* */
@Configuration
public class MailConfig {
    @Value("smtp.gmail.com")
    private String host;

    @Value("587")
    private Integer port;

    @Value("imstudent2210@gmail.com")
    private String email;

    @Value("kfpxhfmutqimkqeh")
    private String password;

    @Value("false")
    private String isSSL;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(email);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", isSSL);
        props.put("mail.smtp.from", email);
        props.put("mail.debug", "true");

        return mailSender;
    }
}
