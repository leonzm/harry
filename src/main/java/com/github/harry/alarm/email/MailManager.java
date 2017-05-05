package com.github.harry.alarm.email;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public interface MailManager {

    void send(String to, String title, String message) throws Exception;

}
