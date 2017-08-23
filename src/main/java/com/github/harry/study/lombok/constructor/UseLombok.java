package com.github.harry.study.lombok.constructor;

import lombok.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: NoArgsConstructor、AllArgsConstructor为该类产生无参的构造方法和包含所有参数的构造方法 <br/>
 * RequiredArgsConstructor 使用类中所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法，还可以用 staticName 生成一个指定名称的静态方法，返回一个调用相应的构造方法产生的对象<br/>
 * 如果类中含有final修饰的成员变量，是无法使用@NoArgsConstructor注解的
 * @Version: 1.0.0
 */
@RequiredArgsConstructor(staticName = "getInstance")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class UseLombok {

    private int x;
    @NonNull
    private double y;
    @NonNull
    private String name;

}
