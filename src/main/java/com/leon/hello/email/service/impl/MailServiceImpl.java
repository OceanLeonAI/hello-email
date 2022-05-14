package com.leon.hello.email.service.impl;

import com.leon.hello.email.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: MailServiceImpl
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/14 23:30
 * @Version 1.0
 * @DESCRIPTION: TODO
 **/
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    /**
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
    }
}
