package com.github.harry.alarm;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class AlarmNotifyException extends RuntimeException {

    public AlarmNotifyException() {
        super();
    }

    public AlarmNotifyException(String message) {
        super(message);
    }

    public AlarmNotifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlarmNotifyException(Throwable cause) {
        super(cause);
    }
}
