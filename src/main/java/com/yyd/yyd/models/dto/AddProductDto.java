package com.yyd.yyd.models.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;

@Data
public class AddProductDto {


    @ApiModelProperty(value = "分类")
    private String categoryId;


    @ApiModelProperty(value = "商品名称")
    private String name;


    @ApiModelProperty(value = "商品图片JSON")
    @JsonProperty("imgUrls")
    private List<String> imgUrls;


    @ApiModelProperty(value = "商品价格")
    @JsonProperty("price")
    private Double price;


    @ApiModelProperty(value = "商品描述")
    private String describe;

}
