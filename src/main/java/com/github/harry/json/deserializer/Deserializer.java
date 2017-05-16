package com.github.harry.json.deserializer;

import java.lang.reflect.Type;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/16
 * @Description:
 * @Version: 1.0.0
 */
public interface Deserializer {

    <T> T deserialize(Object object, Type type);

}
