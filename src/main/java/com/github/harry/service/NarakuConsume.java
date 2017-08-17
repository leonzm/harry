package com.github.harry.service;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description:
 * @Version: 1.0.0
 */
public interface NarakuConsume {

    void subscribe(String topic, Consumer<List<String>> consumer);

    void close();

}
