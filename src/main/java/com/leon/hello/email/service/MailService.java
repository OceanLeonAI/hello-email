package com.leon.hello.email.service;

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
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleEmail(String to, String subject, String content);

}
