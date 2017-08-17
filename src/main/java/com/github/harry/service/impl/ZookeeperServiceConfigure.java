package com.github.harry.service.impl;

import com.github.harry.conf.Configuration;
import com.github.harry.service.ServiceConfigure;
import com.github.harry.util.Assert;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 使用 Zookeeper 服务配置 <br/>
 * 调用 change 方法时，如果 key 在 zookeeper 中不存在，则无效；当 key 删除后，监控终止
 * @Version: 1.0.0
 */
public class ZookeeperServiceConfigure implements ServiceConfigure {

    private static final Logger LOGGER = Logger.getLogger(ZookeeperServiceRegister.class);
    private static final String CONFIGURE_ROOT_NODE_NAME = "configure"; // 配置的根节点

    private ZooKeeper zookeeper;

    private String zookeeperAddress; // Zookeeper 地址
    private String environment; // 环境

    public ZookeeperServiceConfigure(String zookeeperAddress, String environment) throws Exception {
        if (Strings.isNullOrEmpty(zookeeperAddress)) {
            throw new IllegalArgumentException("Paramter of zookeeperAddress can't empty.");
        }
        if (Strings.isNullOrEmpty(environment)) {
            throw new IllegalArgumentException("Paramter of environment can't empty.");
        }
        this.zookeeperAddress = zookeeperAddress;
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

        // 检查路径
        StringBuffer path = new StringBuffer("/".concat(Configuration.ROOT_NODE));
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        path.append("/").append(CONFIGURE_ROOT_NODE_NAME);
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        path.append("/").append(environment);
        if (zookeeper.exists(path.toString(), false) == null) {
            zookeeper.create(path.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    @Override
    public void write(String key, String value) throws Exception {
        Assert.hasLength(key);
        Assert.hasLength(value);

        String confPath = "/".concat(Configuration.ROOT_NODE).concat("/").concat(CONFIGURE_ROOT_NODE_NAME).concat("/").concat(environment).concat("/").concat(key);
        if (zookeeper.exists(confPath, true) == null) {
            zookeeper.create(confPath, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            zookeeper.setData(confPath, value.getBytes(), -1); // -1忽略版本检查
        }
    }

    @Override
    public String read(String key) throws Exception {
        Assert.notNull(key);

        String confPath = "/".concat(Configuration.ROOT_NODE).concat("/").concat(CONFIGURE_ROOT_NODE_NAME).concat("/").concat(environment).concat("/").concat(key);
        if (zookeeper.exists(confPath, true) == null) {
            return null;
        } else {
            byte[] data = zookeeper.getData(confPath, true, null);
            return (data != null && data.length > 0) ? new String(data) : "";
        }
    }

    @Override
    public void change(String key, Consumer<String> consumer) {
        Assert.hasLength(key);
        Assert.notNull(consumer);

        watchConfigureChildren(key, consumer);
    }

    // 监控配置根节点下的配置信息节点
    private void watchConfigureChildren(String childrenNode, Consumer<String> consumer) {
        try {
            String childrenPath = "/".concat(Configuration.ROOT_NODE).concat("/").concat(CONFIGURE_ROOT_NODE_NAME).concat("/").concat(environment).concat("/").concat(childrenNode);
            if (zookeeper.exists(childrenPath, false) == null) {
                return;
            }
            zookeeper.getData(childrenPath, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    LOGGER.info(String.format("%s, node %s", childrenPath, watchedEvent.getType().toString()));
                    if (watchedEvent.getType() == Event.EventType.NodeCreated ||
                            watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                        try {
                            String value = read(childrenNode);
                            consumer.accept(value);
                        } catch (Exception e) {
                            LOGGER.warn("Read configure exception", e);
                        }
                    } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                        consumer.accept(null);
                    }
                    watchConfigureChildren(childrenNode, consumer);
                }
            }, null);
        } catch (Exception e) {
            LOGGER.warn("Zookeeper configure children watch exception", e);
        }
    }

}
