package com.github.harry.handler;

import com.github.harry.constant.CODE;
import com.github.harry.conf.Configuration;
import com.github.harry.json_return.Return;
import com.github.harry.util.EmailUtil;
import com.github.harry.util.Tool_Common;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 文件上传异常捕获
 * @Version: 1.0.0
 */
public class UploadHandlerExceptionResolver implements HandlerExceptionResolver {

    public static final Logger LOGGER = Logger.getLogger(UploadHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        if (e instanceof MaxUploadSizeExceededException) {

            String message = "文件上传大小超过最大限制";
            LOGGER.error(message, e);
            EmailUtil.sendEmail(Configuration.email_receiver, "环境：" + Configuration.environment + "，文件上传大小超过最大限制：" + e.getMessage(), Tool_Common.stacktrace(e));
            try {
                response.getWriter().write(Return.FAIL(CODE.max_upload.code, CODE.max_upload.note).put("message", e.getMessage()).toJson());
            } catch (IOException ex) {}
        }
        return null;
    }

}
