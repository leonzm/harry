package com.github.harry.core.json;

import com.github.harry.core.json.fastjson.FastJSONAdapter;
import com.github.harry.core.json.harryjson.HarryJSONAdapter;
import com.github.harry.core.json.jackson.JacksonJSONAdapter;
import com.github.harry.core.logger.Logger;
import com.github.harry.core.logger.LoggerFactory;
import com.github.harry.core.spi.ServiceLoader;
import com.github.harry.util.StringUtils;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/18
 * @Description:
 * @Version: 1.0.0
 */
public class JSONFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONFactory.class);

    private static volatile JSONAdapter JSON_ADAPTER;

    static {
        String json = System.getProperty("harryjson.json");
        if ("fastjson".equals(json)) {
            setJSONAdapter(new FastJSONAdapter());
        } else if ("jackson".equals(json)) {
            setJSONAdapter(new JacksonJSONAdapter());
        } else {
            try {
                setJSONAdapter(new FastJSONAdapter());
            } catch (Throwable ignored) {
                try {
                    setJSONAdapter(new JacksonJSONAdapter());
                } catch (Throwable ignored2) {
                    try {
                        setJSONAdapter(new HarryJSONAdapter());
                    } catch (Throwable ignored3) {
                        throw new JSONException("Please check JSON lib");
                    }
                }
            }
        }
    }

    public static void setJSONAdapter(String jsonAdapter) {
        if (StringUtils.isNotEmpty(jsonAdapter)) {
            setJSONAdapter(ServiceLoader.load(JSONAdapter.class, jsonAdapter));
        }
    }

    public static JSONAdapter getJSONAdapter() {
        return JSONFactory.JSON_ADAPTER;
    }

    public static void setJSONAdapter(JSONAdapter jsonAdapter) {
        if (jsonAdapter != null) {
            LOGGER.info("Using JSON lib " + jsonAdapter.getName());
            JSONFactory.JSON_ADAPTER = jsonAdapter;
        }
    }
}
