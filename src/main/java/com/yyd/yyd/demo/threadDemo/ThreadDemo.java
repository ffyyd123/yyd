package com.yyd.yyd.demo.threadDemo;

import java.util.concurrent.Callable;

public class ThreadDemo extends Thread implements Runnable {



    @Override
    public void run() {
        System.out.println("1");
    }

//    @Override
//    public Object call() throws Exception {
//        System.out.println("2");
//        return null;
//    }
}
