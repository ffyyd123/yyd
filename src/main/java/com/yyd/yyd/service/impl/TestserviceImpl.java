package com.yyd.yyd.service.impl;

import com.yyd.yyd.dao.TestDao;
import com.yyd.yyd.models.entity.Student;
import com.yyd.yyd.models.entity.Teacher;
import com.yyd.yyd.service.Testservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestserviceImpl implements Testservice {

    @Autowired
    private TestDao testdao;

    @Override
    public List<Student> get() {
        return testdao.get();
    }

    @Override
    public int add(Student student) {
        return testdao.add(student);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return testdao.addTeacher(teacher);
    }
}
