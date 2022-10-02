package com.yyd.yyd.models.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherDto {

    @ApiModelProperty("gid")
    private Integer gid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;
}
