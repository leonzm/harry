package com.github.harry.autoconfigure.resolver;

import com.github.harry.autoconfigure.AutoConfigContext;

import java.beans.PropertyDescriptor;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public interface Resolver {

    void resolve(AutoConfigContext context, PropertyDescriptor descriptor, Class<?> propertyType);

}
