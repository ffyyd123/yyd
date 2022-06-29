package com.yyd.yyd.constants;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yyd.yyd.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * @author yyd
 * @Description: MP自动填充类
 */
@Slf4j
//@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增时填充
     *
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("MyMetaObjectHandler...insertFill:{}", metaObject.toString());
        setFieldValByName("create_time", Utility.getCurrTimestamp(), metaObject);
        setFieldValByName("del_tag", 0, metaObject);
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
        setFieldValByName("edit_time", Utility.getCurrTimestamp(), metaObject);
//        metaObject.setValue("edit_time",Utility.getCurrTimestamp());
    }
}
