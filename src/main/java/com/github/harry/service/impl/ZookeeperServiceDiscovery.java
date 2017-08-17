package com.github.harry.service.impl;

import com.github.harry.conf.Configuration;
import com.github.harry.service.ServiceDiscovery;
import com.github.harry.util.Holder;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.apache.zookeeper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 使用 Zookeeper 服务发现
 * @Version: 1.0.0
 */
public class ZookeeperServiceDiscovery implements ServiceDiscovery {

    private static final Logger LOGGER = Logger.getLogger(ZookeeperServiceDiscovery.class);

    private static Random random = new Random();
    private Holder<List<String>> serviceContains = new Holder<>();

    private ZooKeeper zookeeper;

    private String zookeeperAddress; // Zookeeper 地址
    private String serviceName; // 服务名
    private String environment; // 环境
    private Consumer<List<String>> changeConsumer;

    public ZookeeperServiceDiscovery(String zookeeperAddress, String serviceName, String environment) throws Exception {
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
        this.serviceContains.set(new ArrayList<String>());

        // 连接
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

        // 监控服务
        watchServices();
    }

    // 监控服务
    private void watchServices() {
        try {
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
            List<String> services = zookeeper.getChildren(path.toString(), new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                        watchServices();
                        if (changeConsumer != null) {
                            changeConsumer.accept(serviceContains.get());
                        }
                    }
                }
            });
            if (services == null) {
                services = new ArrayList<>();
            }
            serviceContains.set(services);
        } catch (Exception e) {
            LOGGER.warn("Zookeeper service watch exception", e);
        }
    }

    /**
     * 服务变动 <br/>
     * 当检测出服务变动时，执行supplier函数
     * @param consumer
     */
    public void change(Consumer<List<String>> consumer) {
        this.changeConsumer = consumer;
    }

    /**
     * 服务发现
     * @return
     */
    @Override
    public List<String> discoverys() {
        // 使用 new/序列化 深复制，避免被改动
        List<String> services = new ArrayList<>(serviceContains.get().size());
        serviceContains.get().forEach(service -> {
            services.add(service);
        });
        return services;
    }

    /**
     * 服务发现
     * @return
     */
    @Override
    public String discovery() {
        List<String> service = discoverys();
        if (service != null && service.size() > 0) {
            return service.get(random.nextInt(service.size()));
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String zookeeperAddress = "192.168.1.71:2181,192.168.1.72:2181,192.168.1.73:2181";
        String serviceName = "naraku_test";
        String environment = "develop";
        ServiceDiscovery serviceDiscovery = new ZookeeperServiceDiscovery(zookeeperAddress, serviceName, environment);
        System.out.println(serviceDiscovery.discoverys().size());
        System.out.println(serviceDiscovery.discovery());

        serviceDiscovery.change((services) -> {
            System.out.println("services size: " + services);
        });
        Thread.sleep(Integer.MAX_VALUE);
    }

}
