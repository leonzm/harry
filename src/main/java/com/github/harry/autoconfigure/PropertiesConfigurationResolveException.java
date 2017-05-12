package com.github.harry.autoconfigure;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class PropertiesConfigurationResolveException extends RuntimeException {

    public PropertiesConfigurationResolveException() {
        super();
    }

    public PropertiesConfigurationResolveException(String message) {
        super(message);
    }

    public PropertiesConfigurationResolveException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesConfigurationResolveException(Throwable cause) {
        super(cause);
    }
}
