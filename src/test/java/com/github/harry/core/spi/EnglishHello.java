package com.github.harry.core.spi;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class EnglishHello implements Hello{

    public EnglishHello() {
        System.out.println("EnglishHello init");
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
