package com.github.harry.service;

import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 服务配置
 * @Version: 1.0.0
 */
public interface ServiceConfigure {

    /**
     * 写配置信息
     * @param key
     * @param value
     */
    void write(String key, String value) throws Exception;

    /**
     * 读配置信息
     * @param key
     * @return
     */
    String read(String key) throws Exception;

    /**
     * 配置信息更新时回调
     * @param key
     * @param consumer
     */
    void change(String key, Consumer<String> consumer);

}
