package com.leon.hello.email;

import com.leon.hello.email.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: SendMailTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/14 23:35
 * @Version 1.0
 * @DESCRIPTION: TODO
 **/
@SpringBootTest
public class SendMailTest {

    @Autowired
    private MailService mailService;


    @Test
    public void sendSimpleEmailTest() {

        String to = "945830709@qq.com";
        String subject = "邮件发送测试";
        String content = "这是一封来自126的邮件";
        mailService.sendSimpleEmail(to, subject, content);

        mailService.sendSimpleEmail("beyondjayfir@126.com", subject, content);

    }

}
