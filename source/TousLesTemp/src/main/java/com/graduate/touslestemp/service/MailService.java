package com.graduate.touslestemp.service;

import com.graduate.touslestemp.domain.dto.DataMailDTO;

import jakarta.mail.MessagingException;

/**
 * @File: MailService.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:29 AM
 * @Update: 21/6/2023
 */
public interface MailService {
    /**
     * Sends an HTML-formatted email using the provided dataMail and templateName.
     *
     * @param dataMail     The DataMailDTO object containing email information.
     * @param templateName The name of the template to be used for the email.
     * @throws MessagingException if there is an issue with the email sending process.
     */
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
