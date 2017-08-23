package com.github.harry.study.lombok.data;

import lombok.Data;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: @Data：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 * @Version: 1.0.0
 */
@Data
public class UseLombok {

    private int id;
    private String name;
    private int age;
    private int sex;

}
