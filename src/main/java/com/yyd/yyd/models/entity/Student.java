package com.yyd.yyd.models.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yyd
 * @since 2022-06-29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Student对象", description = "学生表")
@TableName("tb_student")
public class Student extends Model<Student> {
    @TableId("id")
    @ApiModelProperty(value = "id")
    private Integer id; 

    @TableField("gid")
    @ApiModelProperty(value = "gid")
    private Integer gid;

    @TableField("name")
    @ApiModelProperty(value = "name")
    private String name;

    @TableField("age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
