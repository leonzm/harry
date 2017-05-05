package com.github.harry.alarm.email;

import com.github.harry.alarm.AbstractAlarmNotifier;
import com.github.harry.alarm.AlarmNotifyException;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class EmailAlarmNotifier extends AbstractAlarmNotifier<EmailAlarmMessage> {

    private MailManager mailManager;

    public EmailAlarmNotifier(String host, String port, String userName, String password, String adminAddress, boolean sslEnabled) {
        this.mailManager = new SMTPMailManagerImpl(host, port, userName, password, adminAddress, sslEnabled);
    }

    @Override
    protected void doNotice(EmailAlarmMessage message) {
        try {
            mailManager.send(message.getTo(), message.getTitle(), message.getMsg());
        } catch (Exception e) {
            throw new AlarmNotifyException("EmailAlarmNotifier send error", e);
        }
    }
}
