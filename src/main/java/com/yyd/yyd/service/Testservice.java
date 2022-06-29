package com.yyd.yyd.service;

import com.yyd.yyd.models.entity.Student;

import java.util.List;

public interface Testservice {


    List<Student> get();


    int add(Student student);
}
