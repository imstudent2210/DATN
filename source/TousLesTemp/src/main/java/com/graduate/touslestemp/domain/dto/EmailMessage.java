package com.graduate.touslestemp.domain.dto;
/*
* @File:  EmailMessage.java com.graduate.touslestemp.domain.dto
*
* @Author: TamNLT
* @Since: 20/6/2023 11:16 PM
* @Last update: 20/6/2023
*
* */
public class EmailMessage {
    private String to;
    private String subject;
    private String message;

    public EmailMessage() {
    }

    public EmailMessage(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
