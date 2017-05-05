package com.github.harry.core.spi;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
@SPI(key = "test.hello", dftValue = "chinese")
public interface Hello {

    String sayHello();

}
