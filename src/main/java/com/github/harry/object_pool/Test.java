package com.github.harry.object_pool;

import com.github.harry.object_pool.impl.CommonsPool2Impl;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/22
 * @Description: 使用 CommonsPool2Impl 的例子
 * @Version: 1.0.0
 */
public class Test {

    private static AtomicInteger count = new AtomicInteger(0);

    private int num;

    public Test(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Test-" + num;
    }

    public static void main(String[] args) throws Exception {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(10);
        config.setMaxIdle(10);
        config.setMaxTotal(10);

        PooledObjectFactory<Test> factory = new PooledObjectFactory<Test>(){

            @Override
            public PooledObject<Test> makeObject() throws Exception {
                return new DefaultPooledObject(new Test(count.incrementAndGet()));
            }

            @Override
            public void destroyObject(PooledObject<Test> p) throws Exception {

            }

            @Override
            public boolean validateObject(PooledObject<Test> p) {
                return false;
            }

            @Override
            public void activateObject(PooledObject<Test> p) throws Exception {

            }

            @Override
            public void passivateObject(PooledObject<Test> p) throws Exception {

            }
        };

        Pool<Test> pool = new CommonsPool2Impl<>(config, factory);
        for (int i = 0; i < 10; i ++) {
            Test test = pool.borrowObject();
            System.out.println(test);
        }
        //System.out.println(pool.borrowObject()); // 超过对象池最大的容量，会默认一直等待，直到有对象被释放
        System.out.println(pool.borrowObject(4000L)); // 超过对象池最大的容量，除非等待超时（抛出异常）或有对象被释放

    }

}
