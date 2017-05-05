package com.github.harry.core.spi;

import java.lang.annotation.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * config中的键值
     */
    String key() default "";

    /**
     * 默认扩展实现
     */
    String dftValue() default "";

}
