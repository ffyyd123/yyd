package com.yyd.yyd.service;

import com.yyd.yyd.models.entity.Student;
import com.yyd.yyd.models.entity.Teacher;

import java.util.List;

public interface Testservice {


    List<Student> get();


    int add(Student student);

    int addTeacher(Teacher teacher);
}
