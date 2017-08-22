package com.github.harry.object_pool.impl;

import com.github.harry.object_pool.Pool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/22
 * @Description: 基于 commons.pool2 实现的对象池 <br/>
 * 参考：https://my.oschina.net/xinxingegeya/blog/391560
 * @Version: 1.0.0
 */
public class CommonsPool2Impl<T> implements Pool<T> {

    private GenericObjectPool<T> pool;
    private GenericObjectPoolConfig config;
    private PooledObjectFactory<T> factory;

    public CommonsPool2Impl(GenericObjectPoolConfig config, PooledObjectFactory<T> factory) {
        this.pool = new GenericObjectPool<T>(factory, config);
        this.factory = factory;
        this.config = config;
    }

    @Override
    public T borrowObject() throws Exception {
        return pool.borrowObject();
    }

    @Override
    public T borrowObject(long borrowMaxWaitMillis) throws Exception {
        return pool.borrowObject(borrowMaxWaitMillis);
    }

    @Override
    public void returnObject(T o) {
        pool.returnObject(o);
    }

    @Override
    public void destroy() {
        pool.close();
    }

}
