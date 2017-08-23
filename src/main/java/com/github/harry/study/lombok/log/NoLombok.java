package com.github.harry.study.lombok.log;

import org.apache.log4j.Logger;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class NoLombok {

    private static final Logger log = Logger.getLogger(NoLombok.class);

    public static void main(String[] args) {
        log.info("");
    }

}
