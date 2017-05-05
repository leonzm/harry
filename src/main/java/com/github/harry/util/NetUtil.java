package com.github.harry.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class NetUtil {

    /**
     * 获取本地IP
     * @return
     */
    public static String getLocalhostIp() {
        String ip = "unknow";
        try{
            Enumeration<?> enumeration = NetworkInterface.getNetworkInterfaces();
            while(enumeration.hasMoreElements()) {
                NetworkInterface network = (NetworkInterface) enumeration.nextElement();
                Enumeration<?> nextNnumeration = network.getInetAddresses();
                while (nextNnumeration.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) nextNnumeration.nextElement();
                    if(inetAddress.isSiteLocalAddress()){
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return ip;
        } catch(SocketException e) {
            return ip;
        }
    }

}
