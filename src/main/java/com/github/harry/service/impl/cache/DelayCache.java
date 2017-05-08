package com.github.harry.service.impl.cache;

import com.github.harry.service.Cache;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/8
 * @Description: 延迟缓冲池 <br/>
 * 参考：http://www.cnblogs.com/jobs/archive/2007/04/27/730255.html
 * @Version: 1.0.0
 */
public class DelayCache<K, V> implements Cache<K, V> {

    private static final Logger LOGGER = Logger.getLogger(DelayCache.class);

    private Map<K, V> cache = new ConcurrentHashMap<>();
    private DelayQueue<DelayItem<K>> delayQueue = new DelayQueue<>();

    public DelayCache() {
        // 启动一个线程不断移走超时的DelayItem
        Runnable r = new Runnable() {
            @Override
            public void run() {
                LOGGER.info("Delay cache start.");
                cleanCache();
                LOGGER.info("Delay cache end.");
            }
        };
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setName("Delay Cache");
        t.start();
    }

    @Override
    public void add(K key, V value, long time, TimeUnit unit) {
        if (key == null) {
            Preconditions.checkNotNull(key);
        }
        if (value == null) {
            Preconditions.checkNotNull(value);
        }
        if (unit == null) {
            Preconditions.checkNotNull(unit);
        }
        // 如果缓存对象已经存在，覆盖
        if (cache.containsKey(key)) {
            cache.remove(key);
            delayQueue.remove(key);
        }
        cache.putIfAbsent(key, value);
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
        delayQueue.put(new DelayItem<K>(key, nanoTime));
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            Preconditions.checkNotNull(key);
        }
        return cache.containsKey(key);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            Preconditions.checkNotNull(key);
        }
        return cache.get(key);
    }

    // 移走超时的item
    private void cleanCache() {
        while (true) {
            try {
                DelayItem delayItem = delayQueue.take();// 获取并移除此队列的头部，在可从此队列获得到期延迟的元素之前一直等待
                if (delayItem != null) {
                    cache.remove(delayItem.getItem());
                }
            } catch (Exception e) {
                LOGGER.error("Delay cache exception", e);
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DelayCache<String, String> cache = new DelayCache<>();
        cache.add("k1", "v1", 3, TimeUnit.SECONDS);
        cache.add("k2", "v2", 5, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(2L);
        System.out.println("2秒后");
        System.out.println("k1 ==> " + cache.get("k1"));
        System.out.println("k2 ==> " + cache.get("k2"));

        TimeUnit.SECONDS.sleep(2L);
        System.out.println("2秒后");
        System.out.println("k1 ==> " + cache.get("k1"));
        System.out.println("k2 ==> " + cache.get("k2"));

        TimeUnit.SECONDS.sleep(2L);
        System.out.println("2秒后");
        System.out.println("k1 ==> " + cache.get("k1"));
        System.out.println("k2 ==> " + cache.get("k2"));
    }

}