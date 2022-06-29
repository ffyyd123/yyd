package com.yyd.yyd.frame.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @author Ivan
 */
public class Query2Wrapper<T> extends QueryWrapper<T> {

    public Query2Wrapper(boolean flag) {
        if (flag) {
            eq("del_tag", 0);
        }
    }

    public Query2Wrapper() {
        this(true);
    }

    @Override
    protected String columnToString(String column) {
        // 驼峰命名转换为下划线命名
        return StringUtils.camelToUnderline(column);
    }

}
