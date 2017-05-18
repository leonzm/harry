package com.github.harry.core.json.fastjson;

import com.github.harry.core.json.JSONAdapter;
import com.github.harry.core.json.JSONArray;
import com.github.harry.core.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/18
 * @Description:
 * @Version: 1.0.0
 */
public class FastJSONAdapter implements JSONAdapter {

    @Override
    public String getName() {
        return "fastjson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        return com.alibaba.fastjson.JSONObject.parseObject(json, type);
    }

    @Override
    public String toJSONString(Object obj) {
        return com.alibaba.fastjson.JSONObject.toJSONString(obj);
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        com.alibaba.fastjson.JSONObject json = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(obj);
        return new FastJSONObject(json);
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        return new FastJSONArray((com.alibaba.fastjson.JSONArray) com.alibaba.fastjson.JSONArray.toJSON(obj));
    }

    @Override
    public JSONArray parseArray(String json) {
        return new FastJSONArray(com.alibaba.fastjson.JSONObject.parseArray(json));
    }

    @Override
    public JSONObject parseObject(String json) {
        return new FastJSONObject(com.alibaba.fastjson.JSONObject.parseObject(json));
    }

    @Override
    public JSONObject newJSONObject() {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject());
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject(map));
    }

    public JSONObject newJSONObject(int initialCapacity) {
        return new FastJSONObject(new com.alibaba.fastjson.JSONObject(initialCapacity));
    }

    @Override
    public JSONArray newJSONArray() {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray());
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray(list));
    }

    public JSONArray newJSONArray(int initialCapacity) {
        return new FastJSONArray(new com.alibaba.fastjson.JSONArray(initialCapacity));
    }

}
