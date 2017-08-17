package com.github.harry.service;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/17
 * @Description: 服务注册
 * @Version: 1.0.0
 */
public interface ServiceRegister {

    /**
     * 服务注册
     * @param ip 地址
     * @param port http端口
     * @param json 注册的详细信息
     */
    void register(String ip, int port, String json) throws Exception;

}
