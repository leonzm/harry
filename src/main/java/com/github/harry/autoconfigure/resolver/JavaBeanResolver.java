package com.github.harry.autoconfigure.resolver;

import com.github.harry.autoconfigure.AutoConfigContext;
import com.github.harry.autoconfigure.PropertiesConfigurationFactory;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class JavaBeanResolver extends AbstractResolver {

    public static final JavaBeanResolver INSTANCE = new JavaBeanResolver();

    @Override
    public void resolve(AutoConfigContext context, PropertyDescriptor descriptor, Class<?> propertyType) {

        final Map<String, String> includeMap = new HashMap<String, String>();

        doFilter(context, descriptor, new Filter() {
            @Override
            public boolean onCondition(String name, String key, String value) {
                return key.startsWith(name);
            }

            @Override
            public boolean call(String name, String key, String value) {
                String subKey = key.substring(name.length() + 1);
                includeMap.put(subKey, value);
                return true;
            }
        });

        Object value = PropertiesConfigurationFactory.createPropertiesConfiguration(propertyType, null, includeMap);
        if (value != null) {
            writeProperty(context, descriptor, value);
        }
    }
}
