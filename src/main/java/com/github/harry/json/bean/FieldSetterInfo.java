package com.github.harry.json.bean;

import java.lang.reflect.Field;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/16
 * @Description:
 * @Version: 1.0.0
 */
public class FieldSetterInfo {

    private String fieldName;

    private Field field;

    public FieldSetterInfo(String fieldName, Field field) {
        this.fieldName = fieldName;
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
