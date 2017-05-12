package com.github.harry.autoconfigure;

import com.github.harry.util.JsonUtil;

import java.io.IOException;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        TestProperties properties = PropertiesConfigurationFactory.createPropertiesConfiguration(TestProperties.class);
        System.out.println(JsonUtil.toJSON(properties));
    }

}
