package com.github.harry.core.spi;

import org.junit.Test;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class SpiTest {

    @Test
    public void test1() {
        System.out.println("============> test1 start");
        Hello hello = ServiceLoader.loadDefault(Hello.class);
        System.out.println(hello.sayHello());
        System.out.println("============> test1 end");
    }

    @Test
    public void test2() {
        System.out.println("============> test2 start");
        Hello hello = ServiceLoader.load(Hello.class, "chinese");
        System.out.println(hello.sayHello());
        System.out.println("============> test2 end");
    }

    @Test
    public void test3() {
        System.out.println("============> test3 start");
        Hello hello = ServiceLoader.load(Hello.class, "english");
        System.out.println(hello.sayHello());
        System.out.println("============> test3 end");
    }

    @Test
    public void test4() {
        System.out.println("============> test4 start");
        try {
            ServiceLoader.load(Hello.class, "test");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("============> test4 end");
    }

}
