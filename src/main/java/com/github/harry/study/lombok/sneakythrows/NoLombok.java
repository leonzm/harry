package com.github.harry.study.lombok.sneakythrows;

import lombok.Lombok;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class NoLombok implements Runnable {

    public String utf8ToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw Lombok.sneakyThrow(uee);
        }
    }

    public void run() {
        try {
            throw new Throwable();
        } catch (Throwable t) {
            throw Lombok.sneakyThrow(t);
        }
    }

}
