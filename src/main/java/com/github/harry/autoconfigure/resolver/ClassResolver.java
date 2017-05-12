package com.github.harry.autoconfigure.resolver;

import com.github.harry.autoconfigure.AutoConfigContext;
import com.github.harry.autoconfigure.PropertiesConfigurationResolveException;

import java.beans.PropertyDescriptor;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class ClassResolver extends AbstractResolver {

    public static final ClassResolver INSTANCE = new ClassResolver();

    @Override
    public void resolve(final AutoConfigContext context, final PropertyDescriptor descriptor, Class<?> propertyType) {

        doFilter(context, descriptor, new Filter() {
            @Override
            public boolean onCondition(String name, String key, String value) {
                return key.equals(name);
            }

            @Override
            public boolean call(String name, String key, String value) {
                try {
                    Class clazz = Class.forName(value);
                    writeProperty(context, descriptor, clazz);
                } catch (ClassNotFoundException e) {
                    throw new PropertiesConfigurationResolveException(e);
                }
                return false;
            }
        });

    }
}
