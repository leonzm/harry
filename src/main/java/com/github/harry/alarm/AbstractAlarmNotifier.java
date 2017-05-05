package com.github.harry.alarm;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 要保证同一条消息不会被重复发送多次
 * @Version: 1.0.0
 */
public abstract class AbstractAlarmNotifier<T extends AlarmMessage> implements AlarmNotifier<T> {

    @Override
    public final void notice(T message) {
        // TODO

        doNotice(message);
    }

    protected abstract void doNotice(T message);
}
