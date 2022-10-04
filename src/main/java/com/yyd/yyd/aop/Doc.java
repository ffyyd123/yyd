package com.yyd.yyd.aop;

import java.lang.annotation.*;

//METHOD声明在方法上使用
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Doc {
}

