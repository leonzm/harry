package com.github.harry.exception;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description:
 * @Version: 1.0.0
 */
public class RegisterServiceException extends Exception {

    private static final long serialVersionUID = -2235464632547743457L;

    public RegisterServiceException() {}

    public RegisterServiceException(String message) {
        super(message);
    }

}
