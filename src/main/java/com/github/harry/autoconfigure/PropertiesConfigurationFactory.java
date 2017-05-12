package com.github.harry.autoconfigure;

import com.github.harry.autoconfigure.annotation.ConfigurationProperties;
import com.github.harry.autoconfigure.resolver.Resolver;
import com.github.harry.autoconfigure.resolver.ResolverUtils;
import com.github.harry.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class PropertiesConfigurationFactory {

    public static <T> T createPropertiesConfiguration(Class<T> clazz, Map<String, String> propMap) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz should not be null");
        }
        if (!clazz.isAnnotationPresent(ConfigurationProperties.class)) {
            throw new IllegalArgumentException(clazz.getName() + " must annotation with @" + ConfigurationProperties.class.getName());
        }

        ConfigurationProperties annotation = clazz.getAnnotation(ConfigurationProperties.class);
        String prefix = annotation.prefix();

        return createPropertiesConfiguration(clazz, prefix, propMap);
    }

    public static <T> T createPropertiesConfiguration(Class<T> clazz, String prefix, Map<String, String> propMap) {

        T targetObj;
        try {
            targetObj = clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(clazz.getName() + " instance error", e);
        }

        AutoConfigContext configContext = new AutoConfigContextBuilder()
                .setPrefix(prefix)
                .setPropMap(propMap)
                .setTargetObj(targetObj)
                .build();

        Set<String> includeNames = new HashSet<String>();
        if (CollectionUtils.isNotEmpty(configContext.getNameDescriptorMap())) {
            for (String key : propMap.keySet()) {
                for (String name : configContext.getNameDescriptorMap().keySet()) {
                    if (key.startsWith(name)) {
                        includeNames.add(name);
                    }
                }
            }
        }

        for (String includeName : includeNames) {
            PropertyDescriptor descriptor = configContext.getNameDescriptorMap().get(includeName);
            Class<?> propertyType = descriptor.getPropertyType();
            Resolver resolver = ResolverUtils.getResolver(propertyType);
            if (resolver != null) {
                resolver.resolve(configContext, descriptor, propertyType);
            } else {
                throw new PropertiesConfigurationResolveException("Can not find Resolver for type:" + propertyType.getName());
            }
        }
        return targetObj;
    }

    public static <T> T createPropertiesConfiguration(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz should not be null");
        }
        if (!clazz.isAnnotationPresent(ConfigurationProperties.class)) {
            throw new IllegalArgumentException(clazz.getName() + " must annotation with @" + ConfigurationProperties.class.getName());
        }
        ConfigurationProperties annotation = clazz.getAnnotation(ConfigurationProperties.class);
        String[] locations = annotation.locations();
        return createPropertiesConfiguration(clazz, locations);
    }

    public static <T> T createPropertiesConfiguration(Class<T> clazz, String[] locations) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz should not be null");
        }
        if (!clazz.isAnnotationPresent(ConfigurationProperties.class)) {
            throw new IllegalArgumentException(clazz.getName() + " must annotation with @" + ConfigurationProperties.class.getName());
        }
        if (locations == null || locations.length == 0) {
            throw new IllegalArgumentException(clazz.getName() + " must specified the properties locations");
        }
        Properties properties = new Properties();
        for (String location : locations) {
            try {
                properties.load(PropertiesConfigurationFactory.class.getClassLoader().getResourceAsStream(location.trim()));
            } catch (IOException e) {
                throw new IllegalStateException("Load properties [" + location + "] error", e);
            }
        }
        Map<String, String> map = new HashMap<String, String>(properties.size());
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue() == null ? null : entry.getValue().toString();
            if (value != null) {
                map.put(key, value);
            }
        }
        return createPropertiesConfiguration(clazz, map);
    }
}
