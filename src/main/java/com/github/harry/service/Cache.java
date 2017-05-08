package com.github.harry.service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/8
 * @Description: 缓冲池，放入的对象在一段时间内可用
 * @Version: 1.0.0
 */
public interface Cache<K, V> {

    /**
     * 添加缓存对象
     * @param key 对象key
     * @param value 对象value
     * @param time 对象存活时间
     * @param unit 单位
     */
    void add(K key, V value, long time, TimeUnit unit);

    /**
     * 判断对象是否存在缓冲池中
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * 获取缓存对象 <br/>
     * 如果对象不存在或失效，返回null
     * @param key 对象key
     * @return
     */
    V get(K key);

}
