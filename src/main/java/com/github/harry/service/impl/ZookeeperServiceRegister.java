package com.github.harry.service.impl;

import com.github.harry.conf.Configuration;
import com.github.harry.exception.RegisterServiceException;
import com.github.harry.service.ServiceRegister;
import com.github.harry.util.NetUtil;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 使用 Zookeeper 服务注册
 * @Version: 1.0.0
 */
public class ZookeeperServiceRegister implements ServiceRegister {

    private static final Logger LOGGER = Logger.getLogger(ZookeeperServiceRegister.class);

    private ZooKeeper zookeeper;

    private String zookeeperAddress; // Zookeeper 地址
    private String serviceName; // 服务名
    private String environment; // 环境

    public ZookeeperServiceRegister(String zookeeperAddress, String serviceName, String environment) throws Exception {
        if (Strings.isNullOrEmpty(zookeeperAddress)) {
            throw new IllegalArgumentException("Paramter of zookeeperAddress can't empty.");
        }
        if (Strings.isNullOrEmpty(serviceName)) {
            throw new IllegalArgumentException("Paramter of serviceName can't empty.");
        }
        if (Strings.isNullOrEmpty(environment)) {
            throw new IllegalArgumentException("Paramter of environment can't empty.");
        }
        this.zookeeperAddress = zookeeperAddress;
        this.serviceName = serviceName;
        this.environment = environment;

        CountDownLatch latch = new CountDownLatch(1);
        zookeeper = new ZooKeeper(zookeeperAddress, Configuration.ZK_SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    LOGGER.info("Zookeeper connected.");
                    latch.countDown();
                }
            }
        });
        latch.await();
    }

    /**
     * 注册路径：voldemort/环境/服务子项目/ip:port
     * @param ip 地址
     * @param port http端口
     * @param json 注册的详细信息
     * @throws Exception
     */
    @Override
    public void register(String ip, int port, String json) throws Exception {
        if (Strings.isNullOrEmpty(ip)) {
            throw new IllegalArgumentException("Paramter of ip can't empty.");
        }
        json = json != null ? json.trim() : "";
        
        StringBuffer path = new StringBuffer("/".concat(Configuration.ROOT_NODE));
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        path.append("/").append(serviceName);
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        path.append("/").append(environment);
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        path.append("/").append(ip).append(":").append(port);
        if (zookeeper.exists(path.toString(), false) != null) {
            throw new RegisterServiceException(String.format("%s had register", path.toString()));
        }
        zookeeper.create(path.toString(), json.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public static void main(String[] args) throws Exception {
        String zookeeperAddress = "192.168.1.71:2181,192.168.1.72:2181,192.168.1.73:2181";
        String serviceName = "naraku_test";
        String environment = "develop";
        ServiceRegister registerService = new ZookeeperServiceRegister(zookeeperAddress, serviceName, environment);
        registerService.register(NetUtil.getLocalhostIp(), 8080, "{\"environment\":\"develop\"}");

        Thread.sleep(Integer.MAX_VALUE);
    }

}
