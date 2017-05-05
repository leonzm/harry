package com.github.harry.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/5
 * @Description:
 * @Version: 1.0.0
 */
public class Tool_Common {

    /**
     * 获取32位的uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 拼接打印 exception 栈内容
     * @param e 异常类
     * @return
     */
    public static String stacktrace(Throwable e) {
        StringBuilder stack_trace = new StringBuilder();
        while (e != null) {
            String error_message = e.getMessage();
            error_message = error_message == null ? "\r\n" : error_message.concat("\r\n");
            stack_trace.append(error_message);
            stack_trace.append("<br>");
            for (StackTraceElement string : e.getStackTrace()) {
                stack_trace.append(string.toString());
                stack_trace.append("<br>");
            }
            e = e.getCause();
        }
        return stack_trace.toString();
    }

    /**
     * 将链表转换为数组
     * @param list
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list, Class<T> type) {
        if (list == null) {
            return null;
        }
        T[] array = (T[]) Array.newInstance(type, list.size());
        for (int i = 0; i < list.size(); i ++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * md5加盐
     * @param password
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String MD5(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update((password + "{" + salt.toString() + "}").getBytes(Charset.forName("UTF-8")));
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        byte tmp[] = instance.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,>>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return new String(str);
    }

    /**
     * md5加盐
     * @param password
     * @param salt
     * @return
     */
    public static String MD5NoException(String password, String salt) {
        try {
            return MD5(password, salt);
        } catch (Exception e) {}
        return "";
    }

    /**
     * 去掉字符串中间和两端的空格
     * @param string
     * @return
     */
    public static String trim(String string) {
        if (string == null) {
            return null;
        }
        // 去掉特殊空格
        string = string.replace(" ", " ");
        // 并将中间多个连续的空格合并成一个
        String trim = Pattern.compile("[' ']+").matcher(string).replaceAll(" ").trim();
        if (trim.startsWith(" ")) {
            trim = trim.substring(1);
        }
        return trim;
    }

    /**
     * 对传过来的map集合进行排序并按序存放于list 集合中；
     *
     * @author yuan
     * @param map
     * @return
     */
    public static List<Map.Entry<?, Integer>> sort_map_byint(Map<?, Integer> map) {
        List<Map.Entry<?, Integer>> list = new ArrayList<Map.Entry<?, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<?, Integer>>() {

            @Override
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        return list;
    }

    /**
     * 将map按照value进行排序
     *
     * @param map
     * @return
     */
    public static List<Map.Entry<?, Double>> sort_map_bydouble(Map<?, Double> map) {
        List<Map.Entry<?, Double>> list = new ArrayList<Map.Entry<?, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<?, Double>>() {
            @Override
            public int compare(Map.Entry<?, Double> o1, Map.Entry<?, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list;
    }

    /**
     * @is_object_empty
     * @判断是否为空
     * @如果参数为 null 或者参数为空返回 false
     * @param objects 可传多个参数，这里只是判断是否为空
     * @return
     */
    public static Boolean is_object_empty(Object... objects) {
        for(Object obj :objects){
            if (obj == null || trim(obj.toString()).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串判空
     * @param string
     * @return
     */
    public static Boolean is_empty(String string) {
        return string == null || trim(string).isEmpty();
    }

    /**
     * 字符串去空格（前后和中间）
     * @param string
     * @param _default
     * @return
     */
    public static String trim(String string, String _default) {
        if (string == null) {
            return _default;
        }
        return trim(string);
    }

    /**
     * 当前线程阻塞
     * @param millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {}
    }

    /**
     * 获取请求的IP地址
     * @param request
     * @return
     */
    public static String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 获取文件的 md5
     *
     * @param file
     * @return
     */
    public static String md5File(File file) throws IOException, NoSuchAlgorithmException {
        if (file == null || file.isDirectory()) {
            return null;
        }

        try (FileInputStream inputStream = new FileInputStream(file);) {
            MappedByteBuffer byteBuffer = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            return bi.toString(16);
        }
    }

}
