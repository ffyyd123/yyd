package com.yyd.yyd.frame.web;

import java.lang.annotation.*;


@Documented
@Inherited
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreFormatResponse {
}
