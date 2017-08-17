package com.github.harry.conf;

import com.github.harry.util.NetUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.TimeZone;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 项目配置
 * @Version: 1.0.0
 */
public class Configuration {

    public static final Logger LOGGER = Logger.getLogger(Configuration.class);

    /************************ 加载配置文件 ************************/
    private static Properties argsProperties = new Properties();
    static {
        try (InputStream resourceAsStream = Configuration.class.getResourceAsStream("/conf/args.properties");) {
            argsProperties.load(resourceAsStream);
            LOGGER.info("加载配置文件args.properties");
        } catch (IOException e) {
            LOGGER.error("加载配置文件args.properties异常", e);
            System.exit(1);
        }
    }

    /************************ 项目配置 ************************/
    public static final String version = argsProperties.getProperty("harry.version"); // 当前版本
    public static final String environment = argsProperties.getProperty("harry.environment"); // 当前环境
    public static final String email_receiver = argsProperties.getProperty("harry.email.receiver"); // 邮件接受者
    public static final String http_port = argsProperties.getProperty("harry.port"); // http端口
    public static final String zookeeper_address = argsProperties.getProperty("harry.zookeeper.address"); // zookeeper 地址
    public static final String kafka_brokers = argsProperties.getProperty("harry.kafka.brokers"); // kafka brokers 地址

    /************************ 系统配置 ************************/
    public static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT+8");
    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final String IP = NetUtil.getLocalhostIp();
    public static Integer httpPort = Integer.parseInt(http_port);
    public final static int ZK_SESSION_TIMEOUT = 5000; // zk 超时时间（单位：毫秒）
    public static final String ROOT_NODE = "harry"; // 注册的根节点

    /**
     * 项目初始化
     */
    public static void init_config() {
        init_config(null);
    }

    /**
     * 项目初始化
     * @param args
     */
    public static void init_config(String[] args) {
        try {
            LOGGER.info("开始初始化项目配置");
            LOGGER.info("当前版本：" + version);
            LOGGER.info("当前环境：" + environment);
            LOGGER.info("结束初始化项目配置");
        } catch (Exception e) {
            LOGGER.error("初始化项目配置异常", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        init_config();
    }
    
}
