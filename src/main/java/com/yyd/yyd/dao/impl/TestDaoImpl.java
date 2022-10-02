package com.yyd.yyd.dao.impl;

import com.yyd.yyd.dao.TestDao;
import com.yyd.yyd.dao.mapper.StudentMapper;
import com.yyd.yyd.models.entity.Student;
import com.yyd.yyd.models.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    @Resource
    private StudentMapper studentMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Student> get() {
        return studentMapper.get();
    }

    @Override
    public int add(Student student) {
        int insert1 = studentMapper.insert(student);

        return insert1;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        Teacher insert = mongoTemplate.save(teacher);
        System.out.println(insert);
        return 0;
    }
}
