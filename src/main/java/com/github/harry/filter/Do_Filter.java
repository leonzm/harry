package com.github.harry.filter;

import com.github.harry.conf.Configuration;
import com.github.harry.util.EmailUtil;
import com.github.harry.util.Tool_Common;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 编码、异常过滤器
 * @Version: 1.0.0
 */
public class Do_Filter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(Do_Filter.class);

    private String encoding = "UTF-8";
    private String allow = "*";

    /**
     * 读取配置文件，初始化参数
     *
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        // 本过滤器默认编码是UTF-8，但也可以在web.xml配置文件里设置自己需要的编码
        if (filterConfig.getInitParameter("encoding") != null) {
            encoding = filterConfig.getInitParameter("encoding");
        }
        if (filterConfig.getInitParameter("allow") != null) {
            allow = filterConfig.getInitParameter("allow");
        }
    }

    /**
     * 过滤时设置编码、跨域访问和处理未关闭的session
     *
     * @param srequset
     * @param sresponse
     * @param filterChain
     * @throws IOException
     */
    public void doFilter(ServletRequest srequset, ServletResponse sresponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequset;
        HttpServletResponse response = (HttpServletResponse) sresponse;
        //
        request.setCharacterEncoding(encoding);
        // 必须否则前台会收到乱码
        response.setContentType("text/html;charset=".concat(encoding));
        // 指明当前页面支持跨越访问
        response.setHeader("Access-Control-Allow-Origin", allow);

        try {
            filterChain.doFilter(srequset, sresponse);
        } catch (Exception e) {
            LOGGER.error("过滤异常", e);
            EmailUtil.sendEmail(Configuration.email_receiver, "环境：" + Configuration.environment + "，异常：" + e.getMessage(), Tool_Common.stacktrace(e));
        }
    }

    public void destroy() {
        this.encoding = null;
    }

}

