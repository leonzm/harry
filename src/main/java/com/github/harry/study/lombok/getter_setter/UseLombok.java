package com.github.harry.study.lombok.getter_setter;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: @Setter：注解在属性上；为属性提供 setting 方法，@Getter：注解在属性上；为属性提供 getting 方法
 * @Version: 1.0.0
 */
public class UseLombok {

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int age;
    @Getter
    @Setter
    private int sex;

}
