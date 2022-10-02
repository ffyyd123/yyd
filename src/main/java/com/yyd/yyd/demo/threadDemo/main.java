package com.yyd.yyd.demo.threadDemo;

public class main {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();

        threadDemo.run();
        threadDemo.wait();
    }
}
