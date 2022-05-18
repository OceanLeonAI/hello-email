package com.leon.hello.email;

import com.leon.hello.email.service.MailService;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: SendMailTest
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/14 23:35
 * @Version 1.0
 * @DESCRIPTION: 邮件发送单元测试类
 **/
@SpringBootTest
public class SendMailTest {

    private final Logger logger = LoggerFactory.getLogger(SendMailTest.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;


    @Test
    public void sendSimpleTextEmailTest() {
        String to = "beyondjayfir@126.com";
        String subject = "邮件发送测试";
        String content = "这是一封来自126的邮件";
        mailService.sendSimpleTextEmail(to, subject, content);
    }

    @Test
    public void sendHtmlEmailTest() throws MessagingException {
        String to = "beyondjayfir@126.com";
        String subject = "邮件发送测试";
        String content = "";
        content += "<html>";
        content += "<body>";
        content += "<h1>";
        content += "这是一封来自126的html邮件";
        content += "</h1>";
        content += "</body>";
        content += "</html>";
        mailService.sendHtmlEmail(to, subject, content);
    }


    @Test
    public void sendAttachmentEmailTest() throws MessagingException {
        String to = "beyondjayfir@126.com";
        String subject = "发送附件测试";
        String content = "";
        content += "<html>";
        content += "<body>";
        content += "<h1>";
        content += "这是一封来自126的html邮件";
        content += "</h1>";
        content += "</body>";
        content += "</html>";
        String filePath = "D:\\DevelopWorkspace\\WorkspaceForIdea\\hello-email\\src\\test\\resources\\attachment.png";
        mailService.sendAttachmentEmail(to, subject, content, new String[]{filePath, filePath});
    }

    @Test
    public void sendInlineResourceEmailTest() throws MessagingException {
        String to = "beyondjayfir@126.com";
        String subject = "发送带图片邮件测试";
        String resourceId = "image-1";
        String content = "";
        content += "<html>";
        content += "<body>";
        content += "<h1>";
        content += "这是有图片的邮件";
        content += "</h1>";
        content += "<img src='cid:" + resourceId + "'>"; // 注意标签的拼写
        content += "</img>";
        content += "</body>";
        content += "</html>";
        String filePath = "D:\\DevelopWorkspace\\WorkspaceForIdea\\hello-email\\src\\test\\resources\\attachment.png";

        mailService.sendInlineResourceEmail(to, subject, content, filePath, resourceId);
    }

    @Test
    public void sendTemplateEmailTest() throws MessagingException {

        Context context = new Context();
        context.setVariable("name", "OceanLeonAI");
        String mailTemplate = templateEngine.process("emailTemplate", context); // 获得一个html字符串,注意文件名！！！
        logger.info("mailTemplate: {}", mailTemplate);


        String to = "beyondjayfir@126.com";
        String subject = "发送模板邮件测试";

        mailService.sendHtmlEmail(to, subject, mailTemplate);
    }

    @Test
    public void sendGridTest() {
        SendGrid sendGrid = new SendGrid("abc");
        Request request = new Request();
    }

}
