package com.yyd.yyd.demo.demo1;

/**
 * 饿汉式
 */
public class Singleton {

    private static final Singleton singleton=new Singleton();

    private Singleton(){}

    public static Singleton get(){
        return singleton;
    }
}
