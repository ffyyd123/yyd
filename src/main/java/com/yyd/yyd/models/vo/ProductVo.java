package com.yyd.yyd.models.vo;

import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductVo {

    @ApiModelProperty("商品Gid")
    private String productGid;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品价格")
    private Double price;

    @ApiModelProperty("商品图片")
    private String url;
}
