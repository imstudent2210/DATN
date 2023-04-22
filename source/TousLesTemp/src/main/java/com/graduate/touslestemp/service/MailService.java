package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.DataMailDTO;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
