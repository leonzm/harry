package com.github.harry.study.lombok.sneakythrows;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: 注解在方法上；将方法中的代码用try-catch语句包裹起来，捕获异常并在catch中用Lombok.sneakyThrow(e)把异常抛出，可以使用@SneakyThrows(Exception.class)的形式指定抛出哪种异常
 * @Version: 1.0.0
 */
public class UseLombok implements Runnable {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-8");
    }

    @SneakyThrows
    public void run() {
        throw new Throwable();
    }

}
