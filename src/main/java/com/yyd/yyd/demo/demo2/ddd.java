package com.yyd.yyd.demo.demo2;

import com.yyd.yyd.demo.interfacef;

public class ddd {
    public static void main(String[] args) {
       interfacef d= new A();
        B b = new B();
        b.ss();

        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            System.out.println("p");
        }).start();
        System.out.println("d");
    }
//        com.yyd.yyd.demo.demo2.A b = new com.yyd.yyd.demo.demo2.B();
//        System.out.println(b.a);
//        b.fun();

//        int[] arr = {2, 6, 4, 9, 2, 8, 7};
//        int sherlock = function(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//        System.out.println(String.valueOf(arr));
//        System.out.println(sherlock);
    }

//    public static int function(int arr[]) {
//        int length = arr.length;
//        int l = 0;
//        int r = 0;
//        for (int i = 0; i < length - 1; i++) {
//            l = 0;
//            r = 0;
//            for (int j = 0; j < i; j++)
//                l += arr[j];
//            for (int j = i + 1; j < length && l != 0; j++) {
//                r += arr[j];
//            }
//            for (int j = 0; j < arr.length; j++) {
//                System.out.print(arr[j]);
//            }
//            if (l == r && l != 0 && r != 0) {
//                return 0;
//            }
//        }
//        for (int j = 0; j < arr.length; j++) {
//            System.out.print(arr[j]);
//        }
//        return -1;
//    }

