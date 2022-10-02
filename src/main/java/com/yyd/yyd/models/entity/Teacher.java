package com.yyd.yyd.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("teacher")
public class Teacher implements Serializable {
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "age")
    private int age;
    @ApiModelProperty(value = "id")
    private String id;
}
