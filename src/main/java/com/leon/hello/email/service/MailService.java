package com.leon.hello.email.service;

import javax.mail.MessagingException;

/**
 * @PROJECT_NAME: hello-email
 * @CLASS_NAME: MailService
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/14 23:30
 * @Version 1.0
 * @DESCRIPTION: TODO
 **/
public interface MailService {

    /**
     * 发送简单文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleTextEmail(String to, String subject, String content);


    /**
     * 发送Html邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlEmail(String to, String subject, String content) throws MessagingException;

    /**
     * 发送附件邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @throws MessagingException
     */
    void sendAttachmentEmail(String to, String subject, String content, String[] filePaths) throws MessagingException;

    /**
     * 发送带图片的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param resourcePath
     * @param resourceId
     */
    void sendInlineResourceEmail(String to, String subject, String content, String resourcePath, String resourceId) throws MessagingException;

}
