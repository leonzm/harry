package com.github.harry.study.io.aio;

import com.github.harry.study.io.aio.client.Client;
import com.github.harry.study.io.aio.server.Server;

import java.util.Scanner;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        System.out.println("请输入请求消息：");
        Scanner scanner = new Scanner(System.in);

        while(Client.sendMsg(scanner.nextLine()));
    }

}
