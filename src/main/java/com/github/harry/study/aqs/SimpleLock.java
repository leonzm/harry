package com.github.harry.study.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/24
 * @Description: 简单的互斥锁实现 <br/>
 * 参考：http://www.cnblogs.com/zhanjindong/p/java-concurrent-package-aqs-overview.html
 * @Version: 1.0.0
 */
public class SimpleLock extends AbstractQueuedSynchronizer {

    private static final long serialVersionUID = 2802374290470247202L;

    /**
     * 尝试获得锁
     * @param unused
     * @return
     */
    @Override
    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    /**
     * 尝试释放锁
     * @param unused
     * @return
     */
    @Override
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    /**
     * 获取锁
     */
    public void lock() {
        acquire(1);
    }

    /**
     * 是否锁
     */
    public void unlock() {
        release(1);
    }

    // 测试
    public static void main(String[] args) throws Exception {
        SimpleLock lock = new SimpleLock();
        lock.lock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                lock.unlock();
            }).start();
            // 简单的让线程按照for循环的顺序阻塞在lock上
            Thread.sleep(100);
        }

        System.out.println("main thread unlock!");
        lock.unlock();
    }

}
