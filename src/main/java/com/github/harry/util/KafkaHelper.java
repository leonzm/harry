package com.github.harry.util;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: kafka发布订阅简单封装 <br/>
 * @Version: 1.0.0
 */
public class KafkaHelper {

    /**
     * 创建kafka发布者实例
     * @param brokers
     * @return
     */
    public static Producer<String, String> instanceProducer(String brokers) {
        Assert.hasLength(brokers, "Kafka's brokens can't empty.");

        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return new KafkaProducer<>(properties);
    }

    /**
     * 发送消息
     * @param producer
     * @param topics
     * @param value
     * @return
     * @throws Exception
     */
    public static List<Future<RecordMetadata>> send(Producer<String, String> producer, List<String> topics, String value) throws Exception {
        Assert.notNull(producer, "Kafka's producer can't null");
        Assert.notEmpty(topics, "Kafka's topics can't empty.");

        List<Future<RecordMetadata>> futures = new ArrayList<>(topics.size());
        for (String topic : topics) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
            futures.add(producer.send(record));
        }
        return futures;
    }

    /**
     * 创建kafka订阅者实例
     * @param brokers
     * @param groupId
     * @return
     */
    public static Consumer<String, String> instanceConsumer(String brokers, String groupId) {
        Assert.hasLength(brokers, "Kafka's brokens can't empty.");
        Assert.hasLength(groupId, "Kafka's groupId can't empty.");

        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokers);
        properties.put("group.id", groupId);
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        return new KafkaConsumer<>(properties);
    }

    /**
     * 订阅并处理消息
     * @param consumer
     * @param topics
     * @param handler
     * @throws Exception
     */
    public static void subscribe(final Consumer<String, String> consumer, List<String> topics, SubscribeHandler handler) throws Exception {
        Assert.notNull(consumer, "Kafka's consumer can't null");
        Assert.notEmpty(topics, "Kafka's topics can't empty.");
        Assert.notNull(handler, "Kafka's consume handler can't null");

        try {
            consumer.subscribe(topics);
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                        if (records != null && records.count() > 0) {
                            List<String> values = new ArrayList<>(records.count());
                            records.forEach(record -> {values.add(record.value());});
                            handler.handler(records, values);
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
            throw e;
        }
    }

    public interface SubscribeHandler {

        void handler(ConsumerRecords<String, String> records, List<String> values);

    }

}
