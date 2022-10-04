package com.yyd.yyd.constants;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yyd.yyd.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yyd
 * @Description: MP自动填充类
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增时填充
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("MyMetaObjectHandler...insertFill:{}", metaObject.toString());
        setFieldValByName("createTime", new java.sql.Date(new Date().getTime()), metaObject);
        //  this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        setFieldValByName("updateTime", new java.sql.Date(new Date().getTime()), metaObject);
//        setFieldValByName("del_tag", 0, metaObject);
//        metaObject.setValue("create_time",Utility.getCurrTimestamp());
//        metaObject.setValue("del_tag",0);
    }

    /**
     * 修改时填充
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("MyMetaObjectHandler...updateFill:{}", metaObject.toString());
        setFieldValByName("updateTime", new java.sql.Date(new Date().getTime()), metaObject);
//        metaObject.setValue("edit_time",Utility.getCurrTimestamp());
    }
}
