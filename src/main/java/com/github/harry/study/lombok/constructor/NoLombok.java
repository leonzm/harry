package com.github.harry.study.lombok.constructor;

/**
 * @Author: Leon
 * @CreateDate: 2017/8/23
 * @Description:
 * @Version: 1.0.0
 */
public class NoLombok {

    private int x;
    private double y;
    private String name;

    public NoLombok(){
    }

    protected NoLombok(int x,double y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public NoLombok(double y,String name){
        this.y = y;
        this.name = name;
    }

    public static NoLombok getInstance(double y,String name){
        return new NoLombok(y,name);
    }
    
}
