package com.github.harry.util;

import com.github.harry.factory.NamedThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 线程池工具类
 * @Version: 1.0.0
 */
public class ThreadPoolExecutorUtil {

    /**
     * 返回固定大小的线程池
     * @param corePoolSize 池中所保存的线程数，包括空闲线程
     * @param maximumPoolSize 池中允许的最大线程数
     * @param keepAliveTime 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间（单位：秒）
     * @param prefix 线程的前缀名
     * @return
     */
    public static ExecutorService newFixedThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, String prefix) {
        return new ThreadPoolExecutor(corePoolSize, // 池中所保存的线程数，包括空闲线程
                maximumPoolSize, // 池中允许的最大线程数
                keepAliveTime, // 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
                TimeUnit.SECONDS, // keepAliveTime 参数的时间单位
                new LinkedBlockingQueue<Runnable>(maximumPoolSize), // 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。默认与maximumPoolSize一致
                new NamedThreadFactory(prefix, true), // 执行程序创建新线程时使用的工厂
                new ThreadPoolExecutor.DiscardPolicy()); // 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
    }

}
