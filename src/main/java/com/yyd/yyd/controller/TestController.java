package com.yyd.yyd.controller;

import com.yyd.yyd.frame.web.RestResponse;
import com.yyd.yyd.models.dto.StudentDto;
import com.yyd.yyd.models.dto.TeacherDto;
import com.yyd.yyd.models.entity.Student;
import com.yyd.yyd.models.entity.Teacher;
import com.yyd.yyd.service.Testservice;
import com.yyd.yyd.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags = {"测试"})
@RequestMapping("/test")
@Slf4j
@RestController
public class TestController {

    @Autowired
    private Testservice testservice;

    @ApiOperation("查学生")
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RestResponse getDataList() {
        log.info("/data/getDataList  PlanGidDto= {}", "1111");
        List<Student> list = testservice.get();
        return new RestResponse<>(list);
    }

    @ApiOperation("添加学生")
    //@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping("/addStudent")
    public RestResponse addStudent(@RequestBody StudentDto studentDto) {
        log.info("/data/getDataList  PlanGidDto= {}", "1111");
        Student student = new Student();
        student.setId(1);
        BeanUtils.copyProperties(studentDto,student);
        int row = testservice.add(student);
        return new RestResponse<>(row);
    }

    @ApiOperation("添加教师")
    //@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @PostMapping("/addTeacher")
    public RestResponse addTeacher(@RequestBody TeacherDto teacherDto) {
        log.info("/data/getDataList  PlanGidDto= {}", "1111");
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDto,teacher);
        int row = testservice.addTeacher(teacher);
        return new RestResponse<>(row);
    }
}
