package com.github.harry.object_pool;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/22
 * @Description: 对象池接口
 * @Version: 1.0.0
 */
public interface Pool<T> {

    /**
     * 借用对象
     * @return
     * @throws Exception
     */
    T borrowObject() throws Exception;

    /**
     * 借用对象
     * @param borrowMaxWaitMillis 最大等待时间（单位：毫秒）<br/>
     *                            -1表示无限等待<br/>
     *                            如果超过时间还未获取到对象，抛出 NoSuchElementException 异常
     * @return
     * @throws Exception
     */
    T borrowObject(long borrowMaxWaitMillis) throws Exception;

    /**
     * 还回对象
     * @param t
     */
    void returnObject(T t);

    /**
     * 销毁对象
     */
    void destroy();

}
