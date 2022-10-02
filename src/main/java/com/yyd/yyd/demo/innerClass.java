package com.yyd.yyd.demo;

public class innerClass {

    int x=10;

   private static class innerInnerClass{
        int y=20;
        public void test(){
            System.out.println(y);
        }
    }

    public static void main(String[] args) {
        innerInnerClass innerInnerClass = new innerInnerClass();
        int y = innerInnerClass.y;
        System.out.println(y);
    }
}
