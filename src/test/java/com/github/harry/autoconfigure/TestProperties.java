package com.github.harry.autoconfigure;

import com.github.harry.autoconfigure.annotation.ConfigurationProperties;
import com.github.harry.core.spi.SPI;

import java.util.List;
import java.util.Map;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
@ConfigurationProperties(prefix = "test.prop", locations = "autoconfigure.properties")
public class TestProperties {

    private String string;
    private Integer integer;
    private boolean bool;
    private Config config;
    private Map<String, String> map;
    private List<String> list;
    private Integer[] integers;
    private EnumValue enumValue;

    public EnumValue getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(EnumValue enumValue) {
        this.enumValue = enumValue;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public Integer[] getIntegers() {
        return integers;
    }

    public void setIntegers(Integer[] integers) {
        this.integers = integers;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public static class Config {
        private String string;
        private Integer integer;
        private boolean bool;
        private Config config;
        private Map<String, String> map;
        private List<String> list;
        private Integer[] integers;
        private EnumValue enumValue;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        public boolean isBool() {
            return bool;
        }

        public void setBool(boolean bool) {
            this.bool = bool;
        }

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public Integer[] getIntegers() {
            return integers;
        }

        public void setIntegers(Integer[] integers) {
            this.integers = integers;
        }

        public EnumValue getEnumValue() {
            return enumValue;
        }

        public void setEnumValue(EnumValue enumValue) {
            this.enumValue = enumValue;
        }
    }

    public static enum EnumValue {
        ONE,
        TWO,
        THREE
    }
}
