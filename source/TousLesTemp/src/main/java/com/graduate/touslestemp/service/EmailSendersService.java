package com.graduate.touslestemp.service;

public interface EmailSendersService {
    void sendEmail(String to, String subject, String message);
}
