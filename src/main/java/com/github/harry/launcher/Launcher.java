package com.github.harry.launcher;

import com.github.harry.conf.Configuration;
import com.github.harry.util.JettyUtil;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 项目启动器
 * @Version: 1.0.0
 */
public class Launcher {

    public static final int THREAD_NUM_MIN = 50;
    public static final int THREAD_NUM_MAX = 200;

    public static final int PORT = 8080;
    public static final String CONTEXT = "/";
    private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
    private static final String DESCRIPTOR = "src/main/webapp/WEB-INF/web.xml";


    public static void main(String[] args) {
        Configuration.init_config();
        JettyUtil.startJetty(Configuration.httpPort, CONTEXT, DEFAULT_WEBAPP_PATH, DESCRIPTOR, THREAD_NUM_MIN, THREAD_NUM_MAX);
    }

}
