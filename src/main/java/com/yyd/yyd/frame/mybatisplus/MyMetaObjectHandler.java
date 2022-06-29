package com.yyd.yyd.frame.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.yyd.yyd.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 注入bean
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("insert is ok");
        boolean hasGetter = metaObject.hasGetter("creator");
        if (hasGetter) {
            this.setFieldValByName("creator", 1, metaObject);
            this.setFieldValByName("editor", 1, metaObject);
        }
        boolean hasGetterProject = metaObject.hasGetter("projectId");
        if (hasGetterProject) {
            this.setFieldValByName("projectId", 1, metaObject);
        }
        this.setFieldValByName("createTime", Utility.getCurrTimestamp(), metaObject);
        this.setFieldValByName("editTime", Utility.getCurrTimestamp(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("editor", 1, metaObject);
        this.setFieldValByName("editTime", Utility.getCurrTimestamp(), metaObject);
    }
}
