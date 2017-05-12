package com.github.harry.autoconfigure.annotation;

import java.lang.annotation.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description: 和SpringBoot的类似
 * @Version: 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
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
