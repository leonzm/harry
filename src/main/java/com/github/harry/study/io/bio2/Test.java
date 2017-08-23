package com.github.harry.study.io.bio2;

import com.github.harry.study.io.bio.Client;
import com.github.harry.study.io.bio.Server;

import java.io.IOException;
import java.util.Random;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: 测试方法
 * @Version: 1.0.0
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // 运行服务器
        new Thread(() -> {
            try {
                ServerBetter.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        // 避免客户端先于服务器启动前执行代码
        Thread.sleep(100);

        // 运行客户端
        char operators[] = {'+','-','*','/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while (true) {
                // 随机产生算术表达式
                String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                Client.send(expression);
                try {
                    Thread.currentThread().sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
