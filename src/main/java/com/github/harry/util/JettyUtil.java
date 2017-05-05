package com.github.harry.util;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class JettyUtil {

    /**
     * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
     * @param port
     * @param context
     * @param default_webapp_path
     * @param descriptor
     * @param thread_num_min
     * @param thread_num_max
     * @return
     */
    private static Server createServerInSource(int port, String context, String default_webapp_path, String descriptor, int thread_num_min, int thread_num_max) {
        // 设定QTP线程池
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool();
        queuedThreadPool.setDaemon(true);
        queuedThreadPool.setMinThreads(thread_num_min);
        queuedThreadPool.setMaxThreads(thread_num_max);
        Server server = new Server(queuedThreadPool);

        // 设置在JVM退出时关闭Jetty的钩子。
        server.setStopAtShutdown(true);

        // 这是http的连接器
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        // 解决Windows下重复启动Jetty居然不报告端口冲突的问题.
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] { connector });

        WebAppContext webContext = new WebAppContext(default_webapp_path, context);
        // webContext.setContextPath("/");
        webContext.setDescriptor(descriptor);
        // 设置webapp的位置
        webContext.setResourceBase(default_webapp_path);
        webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(webContext);
        return server;
    }

    /**
     * 启动jetty服务
     * @param port
     * @param context
     * @param default_webapp_path
     * @param descriptor
     * @param thread_num_min
     * @param thread_num_max
     */
    public static void startJetty(int port, String context, String default_webapp_path, String descriptor, int thread_num_min, int thread_num_max) {
        final Server server = createServerInSource(port, context, default_webapp_path, descriptor, thread_num_min, thread_num_max);
        try {
            server.stop();
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
