package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.DataMailDTO;

import jakarta.mail.MessagingException;
/*
* @File:  MailService.java com.graduate.touslestemp.service
*
* @Author: TamNLT
* @Since: 20/6/2023 11:29 PM
* @Last update: 20/6/2023
*
* */
public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
