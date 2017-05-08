package com.github.harry.service.impl.cache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/8
 * @Description:
 * @Version: 1.0.0
 */
public class DelayItem<T> implements Delayed {

    // 设置一个开始时间
    private static final long TIME_BEGIN = System.nanoTime();
    // 序列号，保证在 DelayQueue 中的先进先出（FIFO）顺序
    private static final AtomicLong sequence = new AtomicLong(0L);

    private T item;
    private long endTime; // 该 item 的结束时间
    private long sequenceNumber; // 该 item 的序列号

    /**
     *
     * @param item
     * @param timeout 超时时间（单位：毫微秒）
     */
    public DelayItem(T item, long timeout) {
        this.item = item;
        this.endTime = now() + timeout;
        this.sequenceNumber = sequence.getAndIncrement();
    }

    /**
     * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(endTime - now(), TimeUnit.NANOSECONDS);
    }

    /**
     * 比较此对象与指定对象的顺序
     * @param other
     * @return
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0;
        }
        if (other instanceof DelayItem) {
            DelayItem otherItem = (DelayItem) other;
            long diff = this.endTime - otherItem.endTime;
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            } else {
                if (this.sequenceNumber < otherItem.sequenceNumber) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
        long diff = getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
        return (diff == 0) ? 0 : (diff < 0 ? -1 : 1);
    }

    public T getItem() {
        return item;
    }

    // 返回当前"时间"
    private static long now() {
        return System.nanoTime() - TIME_BEGIN;
    }

}