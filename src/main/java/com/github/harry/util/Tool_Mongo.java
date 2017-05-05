package com.github.harry.util;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: Mongo工具
 * @Version: 1.0.0
 */
public class Tool_Mongo {

    private static final Logger LOGGER = Logger.getLogger(Tool_Mongo.class);

    public static final String MONGO_ID = "_id";

    private static final int CONNECTIONS_PER_HOST = 400; // 每个主机的连接数
    private static final int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = 400; // 线程队列数
    private static final int MAX_WAIT_TIME = 10_000; // 最大等待连接的线程阻塞时间
    private static final int CONNECT_TIMEOUT = 10_000; // 连接超时的毫秒。0是默认和无限
    private static final int SOCKET_TIMEOUT = 10_000; // socket超时。0是默认和无限
    // 缓存DBCollection，<dbName, <collectionName, MongoCollection<Document>>>
    private static Holder<Map<String, Map<String, MongoCollection<Document>>>> collectionMap = new Holder<>();
    static {
        collectionMap.set(new HashMap<>());
    }

    private MongoClient mongoClient;
    private String dbAddress;

    public Tool_Mongo(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    /**
     * 初始化MongoClient
     */
    private void initMongoClient() {
        LOGGER.info("初始化MongoClient");
        List<ServerAddress> mongoAddresses = new ArrayList<ServerAddress>();
        for (String address : dbAddress.split(",")) {
            mongoAddresses.add(new ServerAddress(address.split(":")[0].trim(), Integer.parseInt(address.split(":")[1].trim())));
        }

        MongoClientOptions options = null;
        MongoClientOptions.Builder builder = null;
        if (mongoAddresses.size() > 1) { // 副本集
            builder = MongoClientOptions.builder().readPreference(ReadPreference.secondaryPreferred()).writeConcern(new WriteConcern(mongoAddresses.size()));
            LOGGER.info("副本集模式，读副节点，写到主节点，同步" + mongoAddresses.size() + "个节点");
        } else { // 非副本集，不支持REPLICA_ACKNOWLEDGED
            builder = MongoClientOptions.builder().readPreference(ReadPreference.secondaryPreferred()).writeConcern(WriteConcern.ACKNOWLEDGED);
            LOGGER.info("非副本集模式，读副节点，写到主节点");
        }
        builder.connectionsPerHost(CONNECTIONS_PER_HOST); // 每个主机的连接数
        builder.threadsAllowedToBlockForConnectionMultiplier(THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER); // 线程队列数
        builder.maxWaitTime(MAX_WAIT_TIME); // 最大等待连接的线程阻塞时间
        builder.connectTimeout(CONNECT_TIMEOUT); // 连接超时的毫秒。0是默认和无限
        builder.socketTimeout(SOCKET_TIMEOUT); // socket超时。0是默认和无限

        options = builder.build();
        mongoClient = new MongoClient(mongoAddresses, options);
    }

    /**
     * 获取mongo database
     * @param dbName
     * @return
     * @throws UnknownHostException
     */
    private MongoDatabase getDatabase(String dbName) {
        synchronized (Tool_Mongo.class) {
            if (mongoClient == null) {
                initMongoClient();
            }
        }
        return mongoClient.getDatabase(dbName);
    }

    /**
     * 获取DBCollection
     * @param dbName
     * @param collectionName
     * @return
     */
    public MongoCollection<Document> getCollection(String dbName, String collectionName) {
        if (Strings.isNullOrEmpty(dbName) || Strings.isNullOrEmpty(collectionName)) {
            return null;
        }
        if (!collectionMap.get().containsKey(dbName)) {
            collectionMap.get().putIfAbsent(dbName, new HashMap<>());
        }
        if (!collectionMap.get().get(dbName).containsKey(collectionName)) {
            collectionMap.get().get(dbName).putIfAbsent(collectionName, getDatabase(dbName).getCollection(collectionName));
        }
        return collectionMap.get().get(dbName).get(collectionName);
    }

    // 在将待存储的对象转换为json时，需注意Timestamp类型，需转化为Document可识别的类型
    private static Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Timestamp.class, new TypeAdapter<Timestamp>() {
        DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();

        @Override
        public void write(JsonWriter out, Timestamp value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.beginObject().name("$date").value(value.getTime()).endObject();
            }
        }

        @Override
        public Timestamp read(JsonReader in) throws IOException {
            Date date = dateTypeAdapter.read(in);
            if (date == null) {
                return null;
            } else {
                return new Timestamp(date.getTime());
            }
        }
    }).create();

    /**
     * 将对象转换为Json，便于mongo插入，不同之处在于Timestamp的处理，必须让Document识别出日期类型
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object != null) {
            return gson.toJson(object);
        }
        return null;
    }

}
