package com.github.harry.json.deserializer;

import com.github.harry.util.PrimitiveTypeUtils;

import java.lang.reflect.Type;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/16
 * @Description:
 * @Version: 1.0.0
 */
public class PrimitiveTypeDeserializer implements Deserializer {

    @SuppressWarnings("unchecked")
    public <T> T deserialize(Object object, Type type) {
        return PrimitiveTypeUtils.convert(object, type);
    }

}
