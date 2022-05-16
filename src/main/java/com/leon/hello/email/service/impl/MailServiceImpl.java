package com.leon.hello.email.service.impl;

import com.leon.hello.email.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Objects;

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

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

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
    public void sendSimpleTextEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        sender.send(message);
    }

    /**
     * 发送Html邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true); // 注意这个Boolean参数
        sender.send(message);
    }

    /**
     * 发送附件邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @throws MessagingException
     */
    @Override
    public void sendAttachmentEmail(String to, String subject, String content, String[] filePaths) throws MessagingException {
        // 基本信息
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true); // 注意这个Boolean参数

        if (!CollectionUtils.isEmpty(Arrays.asList(filePaths))) {
            Arrays.stream(filePaths).forEach(item -> {
                // 构建附件相关信息
                FileSystemResource fileSystemResource = new FileSystemResource(item);
                // FileSystemResource resource = new FileSystemResource(new File(filePath));
                try {
                    helper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource.getFile());
                } catch (MessagingException e) {
                    logger.error(e.toString());
                }
            });
        }

        sender.send(message);

    }

    /**
     * 发送带图片的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param resourcePath
     * @param resourceId
     */
    @Override
    public void sendInlineResourceEmail(String to, String subject, String content, String resourcePath, String resourceId) throws MessagingException {
        // 基本信息
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true); // 注意这个Boolean参数

        // 添加图片
        helper.addInline(resourceId, new FileSystemResource(resourcePath));

        // 发送邮件
        sender.send(message);
    }
}
