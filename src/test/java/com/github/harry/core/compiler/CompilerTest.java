package com.github.harry.core.compiler;

/**
 * @Author: Leon
 * @CreateDate: 2017/5/12
 * @Description:
 * @Version: 1.0.0
 */
public class CompilerTest {

    public static void main(String[] args) throws Exception {
        //AbstractCompiler compiler = new JdkCompiler();
        AbstractCompiler compiler = new JavassistCompiler();
        Class<?> clazz = compiler.compile("public class Test {\n" +
                "\n" +
                "    public void say(String name) {\n" +
                "        System.out.println(\"Hello \" + name);\n" +
                "    }\n" +
                "\n" +
                "}");
        clazz.getMethod("say", String.class).invoke(clazz.newInstance(), "zhangsan");

    }

}
