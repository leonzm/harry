package com.github.harry.core.logger;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 日志接口 <br/>
 * 声明：引用自commons-logging
 * @Version: 1.0.0
 */
public interface Logger {

    /**
     * 输出跟踪信息
     *
     * @param msg 信息内容
     */
    void trace(String msg);

    /**
     * 输出跟踪信息
     *
     * @param e 异常信息
     */
    void trace(Throwable e);

    /**
     * 输出跟踪信息
     *
     * @param msg 信息内容
     * @param e   异常信息
     */
    void trace(String msg, Throwable e);

    /**
     * 输出跟踪信息
     *
     * @param format    信息内容
     * @param arguments 参数列表
     */
    void trace(String format, Object... arguments);

    /**
     * 输出调试信息
     *
     * @param msg 信息内容
     */
    void debug(String msg);

    /**
     * 输出调试信息
     *
     * @param e 异常信息
     */
    void debug(Throwable e);

    /**
     * 输出调试信息
     *
     * @param msg 信息内容
     * @param e   异常信息
     */
    void debug(String msg, Throwable e);

    /**
     * 输出调试信息
     *
     * @param format    信息内容
     * @param arguments 参数列表
     */
    void debug(String format, Object... arguments);

    /**
     * 输出普通信息
     *
     * @param msg 信息内容
     */
    void info(String msg);

    /**
     * 输出普通信息
     *
     * @param e 异常信息
     */
    void info(Throwable e);

    /**
     * 输出普通信息
     *
     * @param msg 信息内容
     * @param e   异常信息
     */
    void info(String msg, Throwable e);

    /**
     * 输出普通信息
     *
     * @param format    信息内容
     * @param arguments 参数列表
     */
    void info(String format, Object... arguments);

    /**
     * 输出警告信息
     *
     * @param msg 信息内容
     */
    void warn(String msg);

    /**
     * 输出警告信息
     *
     * @param e 异常信息
     */
    void warn(Throwable e);

    /**
     * 输出警告信息
     *
     * @param msg 信息内容
     * @param e   异常信息
     */
    void warn(String msg, Throwable e);

    /**
     * 输出警告信息
     *
     * @param format    信息内容
     * @param arguments 参数列表
     */
    void warn(String format, Object... arguments);

    /**
     * 输出错误信息
     *
     * @param msg 信息内容
     */
    void error(String msg);

    /**
     * 输出错误信息
     *
     * @param e 异常信息
     */
    void error(Throwable e);

    /**
     * 输出错误信息
     *
     * @param msg 信息内容
     * @param e   异常信息
     */
    void error(String msg, Throwable e);

    /**
     * 输出错误信息
     *
     * @param format    信息内容
     * @param arguments 参数列表
     */
    void error(String format, Object... arguments);

    /**
     * 跟踪信息是否开启
     *
     * @return 是否开启
     */
    boolean isTraceEnabled();

    /**
     * 调试信息是否开启
     *
     * @return 是否开启
     */
    boolean isDebugEnabled();

    /**
     * 普通信息是否开启
     *
     * @return 是否开启
     */
    boolean isInfoEnabled();

    /**
     * 警告信息是否开启
     *
     * @return 是否开启
     */
    boolean isWarnEnabled();

    /**
     * 错误信息是否开启
     *
     * @return 是否开启
     */
    boolean isErrorEnabled();
    
}
