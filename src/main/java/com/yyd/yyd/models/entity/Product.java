package com.yyd.yyd.models.entity;

import java.io.Serializable;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yyd
 * @since 2022-10-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Product对象", description = "")
@TableName("tb_product")
public class Product extends Model<Product> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("gid")
    @ApiModelProperty(value = "gid")
    private String gid;

    @TableField("category_id")
    @ApiModelProperty(value = "分类")
    private String categoryId;

    @TableField("name")
    @ApiModelProperty(value = "商品名称")
    private String name;

    @TableField("key_words")
    @ApiModelProperty(value = "关键字")
    private String keyWords;

    @TableField("img_json")
    @ApiModelProperty(value = "商品图片JSON")
    private String imgJson;

    @TableField("price")
    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @TableField("remark")
    @ApiModelProperty(value = "商品描述")
    private String remark;

    @TableField("pv")
    @ApiModelProperty(value = "点击量")
    private Integer pv;

    @TableField("status")
    @ApiModelProperty(value = "商品状态")
    private Integer status;

    @TableField("creator")
    @ApiModelProperty(value = "创建人")
    private String creator;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(value ="update_time",fill = FieldFill.INSERT)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    @TableField(exist = false)
    private String AddTimeStr;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
