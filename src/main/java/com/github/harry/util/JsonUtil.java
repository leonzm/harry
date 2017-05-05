package com.github.harry.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: JSON 操作工具类
 * @Version: 1.0.0
 */
public class JsonUtil {

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将 Java 对象转为 JSON 字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("Java 转 JSON 出错！", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 json 字符串转为 Java 对象
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T fromJSON(String json, TypeReference<T> typeReference) {
        T obj;
        try {
            obj = objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            LOGGER.error("TypeReference 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 将 InputStream 字符串转为 Java 对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("Class 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 将 InputStream 字符串转为 Java 对象
     * @param inputStream
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(InputStream inputStream, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(inputStream, type);
        } catch (Exception e) {
            LOGGER.error("InputStream 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

}
