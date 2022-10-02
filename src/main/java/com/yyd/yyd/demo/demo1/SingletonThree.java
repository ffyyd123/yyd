package com.yyd.yyd.demo.demo1;

/**
 * 双检索
 */
public class SingletonThree {

    private static SingletonThree singletonThree;

    private SingletonThree() {
    }

    public static SingletonThree get() {
        if (singletonThree == null) {
            synchronized (SingletonThree.class) {
                if (singletonThree == null) {
                    return new SingletonThree();
                }
            }
        }
        return singletonThree;
    }
}
