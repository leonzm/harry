package com.github.harry.service;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 服务发现
 * @Version: 1.0.0
 */
public interface ServiceDiscovery {

    /**
     * 服务变动
     * @param consumer
     */
    void change(Consumer<List<String>> consumer);

    /**
     * 服务发现
     * @return
     */
    List<String> discoverys();

    /**
     * 服务发现
     * @return
     */
    String discovery();

}
