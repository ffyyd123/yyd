package com.yyd.yyd.dao.mapper;

import com.yyd.yyd.models.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author yyd
 * @since 2022-06-29
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> get();

}
