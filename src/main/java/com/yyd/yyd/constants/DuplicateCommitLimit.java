package com.yyd.yyd.constants;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DuplicateCommitLimit {

    /**
     * 拦截时间，单位为秒，默认值1秒
     */
    long time() default 1;
}
