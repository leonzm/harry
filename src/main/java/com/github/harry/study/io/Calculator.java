package com.github.harry.study.io;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public final class Calculator {

    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(cal("1 + 1"));
    }

}
