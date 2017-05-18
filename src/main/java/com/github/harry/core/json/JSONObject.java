package com.github.harry.core.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/18
 * @Description:
 * @Version: 1.0.0
 */
public interface JSONObject {

    int size();

    boolean isEmpty();

    boolean containsKey(String key);

    boolean containsValue(Object value);

    Object get(String key);

    JSONObject getJSONObject(String key);

    JSONArray getJSONArray(String key);

    <T> T getObject(String key, Class<T> clazz);

    Boolean getBoolean(String key);

    byte[] getBytes(String key);

    boolean getBooleanValue(String key);

    Byte getByte(String key);

    byte getByteValue(String key);

    Short getShort(String key);

    short getShortValue(String key);

    Integer getInteger(String key);

    int getIntValue(String key);

    Long getLong(String key);

    long getLongValue(String key);

    Float getFloat(String key);

    float getFloatValue(String key);

    Double getDouble(String key);

    double getDoubleValue(String key);

    BigDecimal getBigDecimal(String key);

    BigInteger getBigInteger(String key);

    String getString(String key);

    Date getDate(String key);

    java.sql.Date getSqlDate(String key);

    java.sql.Timestamp getTimestamp(String key);

    Object put(String key, Object value);

    void putAll(Map<? extends String, ? extends Object> m);

    void clear();

    Object remove(String key);

    Set<String> keySet();

    Collection<Object> values();

    Set<Map.Entry<String, Object>> entrySet();

    String toJSONString();

    String toString();

}
