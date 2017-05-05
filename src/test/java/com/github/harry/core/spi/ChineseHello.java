package com.github.harry.core.spi;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class ChineseHello implements Hello{

    public ChineseHello() {
        System.out.println("ChineseHello init");
    }

    @Override
    public String sayHello() {
        return "你好";
    }
}
