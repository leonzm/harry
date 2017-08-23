package com.github.harry.study.io.aio.server;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: AIO服务端
 * @Version: 1.0.0
 */
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static AsyncServerHandler serverHandle;
    public volatile static long clientCount = 0;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if (serverHandle != null)
            return;
        serverHandle = new AsyncServerHandler(port);
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args) {
        Server.start();
    }

}
