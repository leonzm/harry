package com.github.harry.util;

import com.google.common.base.Strings;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 邮件发送工具
 * @Version: 1.0.0
 */
public class EmailUtil {

    private static final Logger LOGGER = Logger.getLogger(EmailUtil.class);

    /************************ 加载配置文件 ************************/
    private static Properties emailProperties = new Properties();
    static {
        try (InputStream resourceAsStream = EmailUtil.class.getResourceAsStream("/conf/email.properties");) {
            emailProperties.load(resourceAsStream);
            LOGGER.info("加载配置文件email.properties");
        } catch (IOException e) {
            LOGGER.error("加载配置文件email.properties异常", e);
            System.exit(1);
        }
    }

    private static String hostname = emailProperties.getProperty("mail.smtp.host");
    private static int smtpport = 465;
    private static boolean sslonconnect = true;
    private static String username = emailProperties.getProperty("mail.username");
    private static String password = emailProperties.getProperty("mail.password");

    /**
     * 邮件发送
     * @param to
     * @param subject
     * @param message
     */
    public static void sendEmail(String to, String subject, String message) {
        if (to.contains(",")) {
            Arrays.asList(to.split(",")).parallelStream().distinct().forEach(subTo -> {_sendEmail(subTo.trim(), subject, message);});
        } else {
            _sendEmail(to, subject, message);
        }
    }

    /**
     * 邮件发送
     * @param to
     * @param subject
     * @param message
     */
    private static void _sendEmail(String to, String subject, String message) {
        if (Strings.isNullOrEmpty(to) || Strings.isNullOrEmpty(subject) || Strings.isNullOrEmpty(message)) {
            return;
        }
        try {
            Email email = new SimpleEmail();
            email.setHostName(hostname);
            email.setSmtpPort(smtpport);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(sslonconnect);
            email.setFrom(username);
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(message);
            email.send();
        } catch (EmailException e) {
            LOGGER.warn("发送邮件异常", e);
        }
    }

}
