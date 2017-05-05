package com.github.harry.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: JSON 操作工具类
 * @Version: 1.0.0
 */
public class GsonUtil {

    public static final Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(String.class, new StringAdapter()).registerTypeHierarchyAdapter(Class.class, new ClassAdapter()).registerTypeHierarchyAdapter(Calendar.class,new CalendarAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().setExclusionStrategies(new MyExclusionStrategy()).create();

    private static class StringAdapter implements JsonSerializer<String>, JsonDeserializer<String> {
        @Override
        public JsonElement serialize(String string, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(string);
        }

        @Override
        public String deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            return element.getAsJsonPrimitive().getAsString();
        }

    }

    private static class ClassAdapter implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {
        @Override
        public JsonElement serialize(Class<?> clazz, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(clazz.getName());
        }

        @Override
        public Class<?> deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            try {
                return Class.forName(element.getAsJsonPrimitive().getAsString());
            } catch (ClassNotFoundException e) {
                return null;
            }
        }

    }

    private static class CalendarAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
        private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        @Override
        public JsonElement serialize(Calendar cal, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(df.format(cal.getTime()));
        }

        @Override
        public Calendar deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(df.parse(element.getAsJsonPrimitive().getAsString()));
            } catch (ParseException e) {
                return null;
            }
            return cal;
        }

    }

    private static class MyExclusionStrategy implements ExclusionStrategy {

        @Override
        public boolean shouldSkipField(FieldAttributes fa) {
            return fa.getName().startsWith("_"); // <---
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    }

}
