package com.github.harry.exception;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class HttpFailException extends Exception {

    private static final long serialVersionUID = -6245874632676835634L;

    private Integer code;
    private String message;

    public HttpFailException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HTTP调用时response.isSuccessful()出现为false的错误 code:".concat(code.toString()).concat(" - message:".concat(message));
    }

}
