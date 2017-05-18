package com.github.harry.core.json.harryjson;

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
public class HarryJSONAdapter implements JSONAdapter {

    @Override
    public String getName() {
        return "ltsjson";
    }

    @Override
    public <T> T parse(String json, Type type) {
        return com.github.harry.json.JSONObject.parseObject(json, type);
    }

    @Override
    public String toJSONString(Object obj) {
        return com.github.harry.json.JSONObject.toJSONString(obj);
    }

    @Override
    public JSONObject toJSONObject(Object obj) {
        com.github.harry.json.JSONObject jsonObject = new com.github.harry.json.JSONObject(obj);
        return new HarryJSONObject(jsonObject);
    }

    @Override
    public JSONArray toJSONArray(Object obj) {
        com.github.harry.json.JSONArray jsonArray = new com.github.harry.json.JSONArray(obj);
        return new HarryJSONArray(jsonArray);
    }

    @Override
    public JSONArray parseArray(String json) {
        return new HarryJSONArray(new com.github.harry.json.JSONArray(json));
    }

    @Override
    public JSONObject parseObject(String json) {
        return new HarryJSONObject(new com.github.harry.json.JSONObject(json));
    }

    @Override
    public JSONObject newJSONObject() {
        return new HarryJSONObject(new com.github.harry.json.JSONObject());
    }

    @Override
    public JSONObject newJSONObject(Map<String, Object> map) {
        return new HarryJSONObject(new com.github.harry.json.JSONObject(map));
    }

    @Override
    public JSONObject newJSONObject(int initialCapacity) {
        return new HarryJSONObject(new com.github.harry.json.JSONObject(initialCapacity));
    }

    @Override
    public JSONArray newJSONArray() {
        return new HarryJSONArray(new com.github.harry.json.JSONArray());
    }

    @Override
    public JSONArray newJSONArray(List<Object> list) {
        return new HarryJSONArray(new com.github.harry.json.JSONArray(list));
    }

    @Override
    public JSONArray newJSONArray(int initialCapacity) {
        return new HarryJSONArray(new com.github.harry.json.JSONArray(initialCapacity));
    }
    
}
