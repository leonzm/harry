package com.github.harry.json;

import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robert HG (254963746@qq.com) on 12/29/15.
 */
public class JSONTest {

    @Test
    public void testMap() throws Exception {

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("xxx", 22222);
        map.put("xxct", 432432);
        map.put("fasdfads", null);

        String json = new JSONObject(map).toString();

        System.out.println(json);
        Map<String, Integer> tmap = JSONObject.parseObject(json, new TypeReference<HashMap<String, Integer>>() {
        }.getType());
        System.out.println(tmap);
    }

}
