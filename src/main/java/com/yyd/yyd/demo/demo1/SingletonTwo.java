package com.yyd.yyd.demo.demo1;

/**
 * 懒汉式
 */
public class SingletonTwo {

    private static SingletonTwo singletonTwo;

    private SingletonTwo() {
    }

    public static SingletonTwo get() {
        if (singletonTwo == null) {
            return new SingletonTwo();
        }
        return singletonTwo;
    }

}
