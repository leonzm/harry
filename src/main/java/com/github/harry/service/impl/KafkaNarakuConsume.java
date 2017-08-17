package com.github.harry.service.impl;

import com.github.harry.service.NarakuConsume;
import com.github.harry.util.Assert;
import com.github.harry.util.Holder;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description:
 * @Version: 1.0.0
 */
public class KafkaNarakuConsume implements NarakuConsume {

    private static final Logger LOGGER = Logger.getLogger(KafkaNarakuConsume.class);

    public static final long PULL_TIMEOUT = 1000L; // 每次阻塞的最长时间（单位：毫秒）

    private static AtomicInteger identifier = new AtomicInteger(1); // 消费标识，便于调试

    private KafkaConsumer<String, String> consumer;
    private Holder<Boolean> started = new Holder<>();

    public KafkaNarakuConsume(String brokers, String groupId) {
        Assert.hasLength(brokers);
        Assert.hasLength(groupId);

        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("group.id", groupId);
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(properties);
        this.started.set(true);
    }

    @Override
    public void subscribe(String topic, Consumer<List<String>> handler) {
        Assert.hasLength(topic);
        Assert.notNull(handler);

        consumer.subscribe(Arrays.asList(topic));
        Integer currentIdentifier = identifier.getAndIncrement();
        new Thread() {
            @Override
            public void run() {
                LOGGER.info(String.format("consumer[%d][%s] 开始消费", currentIdentifier, topic));
                while (started.get()) {
                    try {
                        ConsumerRecords<String, String> records = consumer.poll(PULL_TIMEOUT);
                        if (records != null && records.count() > 0) {
                            List<String> values = new ArrayList<>(records.count());
                            records.forEach(record -> {values.add(record.value());});
                            handler.accept(values);
                        }
                    } catch (Exception e) {
                        LOGGER.warn("处理订阅信息消息异常", e);
                    }
                }
                LOGGER.info(String.format("consumer[%d][%s] 结束消费", currentIdentifier, topic));
            }
        }.start();
    }

    @Override
    public void close() {
        if (consumer != null) {
            try {
                started.set(false);
                consumer.unsubscribe();
                consumer.wakeup(); // 中断活动的轮询
                consumer.close();
            } catch (Exception e) {}
        }
    }

}
