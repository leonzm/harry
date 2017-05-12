package com.github.harry.autoconfigure.annotation;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description: 和SpringBoot的类似
 * @Version: 1.0.0
 */
public @interface ConfigurationProperties {

    /**
     * 前缀
     */
    String prefix() default "";

    /**
     * 直接指定文件位置
     */
    String[] locations() default {};

}
