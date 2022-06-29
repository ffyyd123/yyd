package com.yyd.yyd.dao.impl;

import com.yyd.yyd.dao.TestDao;
import com.yyd.yyd.dao.mapper.StudentMapper;
import com.yyd.yyd.models.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> get() {
        return studentMapper.get();
    }

    @Override
    public int add(Student student) {
        return studentMapper.insert(student);
    }
}
