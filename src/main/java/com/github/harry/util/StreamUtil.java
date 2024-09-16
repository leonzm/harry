package com.github.harry.util;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合流工具
 *
 * @author Leon
 * @since 2024/9/16
 */
public class StreamUtil {

    /**
     * 获取流
     *
     * @param collection Collection
     * @return 流
     */
    public static <T> Stream<T> of(Collection<T> collection) {
        return Optional.ofNullable(collection).orElse(Collections.emptyList()).stream();
    }

    /**
     * 获取流
     *
     * @param collection      Collection
     * @param otherCollection 备选 Collection
     * @return 流
     */
    public static <T> Stream<T> of(Collection<T> collection, Collection<T> otherCollection) {
        return Optional.ofNullable(collection).orElse(otherCollection).stream();
    }

    /**
     * Collection 转换成 Map
     *
     * @param collection  Collection
     * @param keyMapper   Map 键
     * @param valueMapper Map 值
     * @return Map<K, V>
     */
    public static <T, K, U> Map<K, U> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper) {
        return toMap(collection, keyMapper, valueMapper, (a, b) -> a, HashMap::new);
    }

    /**
     * Collection 转换成 Map
     *
     * @param collection    Collection
     * @param keyMapper     Map 键
     * @param valueMapper   Map 值
     * @param mergeFunction 键相同时合并策略
     * @return Map<K, V>
     */
    public static <T, K, U> Map<K, U> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends U> valueMapper,
                                            BinaryOperator<U> mergeFunction) {
        return toMap(collection, keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

    /**
     * Collection 转换成 Map
     *
     * @param collection    Collection
     * @param keyMapper     Map 键
     * @param valueMapper   Map 值
     * @param mergeFunction 键相同时合并策略
     * @return Map<K, V>
     */
    public static <T, K, U, M extends Map<K, U>> Map<K, U> toMap(Collection<T> collection,
                                                                 Function<? super T, ? extends K> keyMapper,
                                                                 Function<? super T, ? extends U> valueMapper,
                                                                 BinaryOperator<U> mergeFunction,
                                                                 Supplier<M> mapSupplier) {
        return Optional.ofNullable(collection).orElse(Collections.emptyList()).stream()
                .collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapSupplier));
    }

    /**
     * Collection 分组
     *
     * @param collection Collection
     * @param classifier 分类器
     * @return Map<K, List<T>>
     */
    public static <T, K> Map<K, List<T>> groupingBy(Collection<T> collection,
                                                    Function<? super T, ? extends K> classifier) {
        return groupingBy(collection, classifier, HashMap::new, Collectors.toList());
    }

    /**
     * Collection 分组
     *
     * @param collection Collection
     * @param classifier 分类器
     * @param mapFactory Map 工厂
     * @param downstream 分组合并流
     * @return Map<K, D>
     */
    public static <T, K, D, A, M extends Map<K, D>> Map<K, D> groupingBy(Collection<T> collection,
                                                                         Function<? super T, ? extends K> classifier,
                                                                         Supplier<M> mapFactory,
                                                                         Collector<? super T, A, D> downstream) {
        return Optional.ofNullable(collection).orElse(Collections.emptyList()).stream()
                .collect(Collectors.groupingBy(classifier, mapFactory, downstream));
    }

}
