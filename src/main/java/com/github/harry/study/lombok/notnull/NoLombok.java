package com.github.harry.study.lombok.notnull;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class NoLombok {

    public static String setObject(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj.toString();
    }

    public static void main(String[] args) {
        System.out.println(setObject(null));
    }

}
