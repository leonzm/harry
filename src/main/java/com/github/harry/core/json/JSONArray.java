package com.github.harry.core.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/18
 * @Description:
 * @Version: 1.0.0
 */
public interface JSONArray {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<Object> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean add(Object e);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends Object> c);

    boolean addAll(int index, Collection<? extends Object> c);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

    Object set(int index, Object element);

    void add(int index, Object element);

    Object remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<Object> listIterator();

    ListIterator<Object> listIterator(int index);

    List<Object> subList(int fromIndex, int toIndex);

    Object get(int index);

    JSONObject getJSONObject(int index);

    JSONArray getJSONArray(int index);

    <T> T getObject(int index, Class<T> clazz);

    Boolean getBoolean(int index);

    boolean getBooleanValue(int index);

    Byte getByte(int index);

    byte getByteValue(int index);

    Short getShort(int index);

    short getShortValue(int index);

    Integer getInteger(int index);

    int getIntValue(int index);

    Long getLong(int index);

    long getLongValue(int index);

    Float getFloat(int index);

    float getFloatValue(int index);

    Double getDouble(int index);

    double getDoubleValue(int index);

    BigDecimal getBigDecimal(int index);

    BigInteger getBigInteger(int index);

    String getString(int index);

    java.util.Date getDate(int index);

    java.sql.Date getSqlDate(int index);

    java.sql.Timestamp getTimestamp(int index);

}
