package com.github.harry.study.lombok.cleanup;

import lombok.Cleanup;

import java.io.*;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description: @Cleanup：注解在变量上；保证此变量代表的资源会被自动关闭，默认是调用资源的close()方法（如果是其它方法，使用@Cleanup(“methodName”)来指定要调用的方法）
 * @Version: 1.0.0
 */
public class UseLombok {

    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);
        byte[] b = new byte[1024];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
    }

}
