package com.github.harry.study.lombok.log;

import lombok.extern.log4j.Log4j;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: @Log4j：注解在类上；为类提供一个 属性名为 log 的 log4j 日志对象
 * @Version: 1.0.0
 */
@Log4j
public class UseLombok {

    public static void main(String[] args) {
        log.info("@Log4j：注解在类上；为类提供一个 属性名为 log 的 log4j 日志对象");
    }

}
