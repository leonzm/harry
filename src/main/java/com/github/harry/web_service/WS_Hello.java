package com.github.harry.web_service;

import com.github.harry.constant.CODE;
import com.github.harry.json_return.Return;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description: 测试接口
 * @Version: 1.0.0
 */
@RestController
@RequestMapping("/ws_hello")
public class WS_Hello {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public Return hello(HttpServletRequest request, HttpServletResponse response, @PathVariable String name) {
        return Return.SUCCESS(CODE.success.code, "Hello ".concat(name));
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Return hello2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        return Return.SUCCESS(CODE.success.code, "Hello ".concat(name));
    }

}
