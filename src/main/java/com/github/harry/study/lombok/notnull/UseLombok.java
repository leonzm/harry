package com.github.harry.study.lombok.notnull;

import com.sun.istack.internal.NotNull;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: @NonNull：注解在属性上（和 @Setter 上）或注解在参数上；非空检查，如果为空，抛出 NullPointerException
 * @Version: 1.0.0
 */
public class UseLombok {

    public static String setObject(@NotNull Object obj) {
        return obj.toString();
    }

    public static void main(String[] args) {
        System.out.println(setObject(null));
    }

}
