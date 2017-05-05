package com.github.harry.alarm;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public interface AlarmNotifier<T extends AlarmMessage> {

    /**
     * 告警发送通知
     */
    void notice(T message);

}
