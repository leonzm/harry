package com.github.harry.study.lombok.synch;

import lombok.Synchronized;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: 注解在方法上；效果和synchronized关键字相同，区别在于锁对象不同，对于类方法和实例方法，synchronized关键字的锁对象分别是类的class对象和this对象，而@Synchronized得锁对象分别是私有静态final对象LOCK和私有final对象lock，当然，也可以自己指定锁对象
 * @Version: 1.0.0
 */
public class UseLombok {

    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }

}
