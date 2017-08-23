package com.github.harry.study.io.nio;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class Server {

    private static int DEFAULT_PORT = 12345;
    private static ServerHandle serverHandle;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if(serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandle(port);
        new Thread(serverHandle, "Server").start();
    }

    public static void main(String[] args){
        start();
    }

}
