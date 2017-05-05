package com.github.harry.core.logger.support;

import com.github.harry.core.logger.Logger;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 扩展多参数Logger
 * @Version: 1.0.0
 */
public abstract class AbstractLogger implements Logger {

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            trace(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            debug(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            info(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            warn(ft.getMessage(), ft.getThrowable());
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
            error(ft.getMessage(), ft.getThrowable());
        }
    }
}
