package com.github.harry.core.logger.slf4j;

import com.github.harry.core.logger.Level;
import com.github.harry.core.logger.Logger;
import com.github.harry.core.logger.LoggerAdapter;

import java.io.File;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class Slf4jLoggerAdapter implements LoggerAdapter {

    public Logger getLogger(String key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    public Logger getLogger(Class<?> key) {
        return new Slf4jLogger(org.slf4j.LoggerFactory.getLogger(key));
    }

    private Level level;

    private File file;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
