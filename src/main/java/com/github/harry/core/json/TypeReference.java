package com.github.harry.core.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/18
 * @Description:
 * @Version: 1.0.0
 */
public abstract class TypeReference<T> {

    private final Type type;

    public TypeReference() {
        Type superClass = getClass().getGenericSuperclass();

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

}
