package com.yyd.yyd.controller;

import com.yyd.yyd.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Demo son = new son("111");
        son.say();

        int x = 3, y = 10;
        System.out.println((x++) + (++y) + y);

        int a[][] = new int[5][2];
        for (int i = 0; i < 5; i++) {
            a[i][0] = i;
            if (i > 0) a[i][1] = a[i][0] + a[i - 1][1];
        }
        System.out.println(a[4][1]);
        JieKouSon jieKouSon = new JieKouSon();

        Boolean d = null, b = false, c = true;
        //  System.out.println(c | d);
        //  System.out.println(b || d);
        //  System.out.println(c &&d);
        System.out.println(b || c || d);

        List<String> integers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            integers.add(i + "..");
        }
        System.err.println(JsonUtils.toJson(integers));
        integers.remove(2);
        String xx = "7..";
        integers.remove(xx);
//        for (Integer integer : integers) {
//            integers.remove(integer);
//        }
        for (Iterator it = integers.iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
        System.out.println(JsonUtils.toJson(integers));

        System.out.println("和是：" + add(9, 34));
    }

    public static int add(int a, int b) {
        try {
            System.out.println("开始计算");
            return a + b;
        } catch (Exception e) {
            System.out.println("catch 语句块");
        } finally {
            System.out.println("finally 语句块");
        }
        return 0;
    }
}
