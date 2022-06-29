package com.yyd.yyd.constants.datasource;

/**
 * @author Yanxu
 * @Date 2018/4/20 16:31
 */

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadSlave {
    /**
     * 设置读取主库还是从库  默认是随机从库
     */
    RunDatabaseType value() default RunDatabaseType.RADOMSlAVE;

    /**
     *  如果设置执行指定从库，这个字段要设置，默认执行从库1
     */
    int slaveNum() default 0;
}
