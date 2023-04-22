package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.ClientSdi;
import com.graduate.touslestemp.domain.dto.DataMailDTO;
import com.graduate.touslestemp.service.ClientService;
import com.graduate.touslestemp.service.MailService;
import com.graduate.touslestemp.constant.SendMailConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(SendMailConstant.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getDisplayName());
            props.put("username", sdi.getUsername());
            props.put("password", sdi.getPassword());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, SendMailConstant.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }
}
