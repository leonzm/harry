package com.github.harry.json.bean;

import java.lang.reflect.Method;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/16
 * @Description:
 * @Version: 1.0.0
 */
public class MethodInfo {

    private String fieldName;

    private Method method;

    public MethodInfo(String fieldName, Method method) {
        this.fieldName = fieldName;
        this.method = method;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
