package com.yyd.yyd.controller;

import com.yyd.yyd.aop.Doc;
import com.yyd.yyd.frame.web.RestResponse;
import com.yyd.yyd.models.dto.AddProductDto;
import com.yyd.yyd.models.entity.Student;
import com.yyd.yyd.models.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

import lombok.var;

import java.util.List;

import com.yyd.yyd.service.ProductService;
import com.yyd.yyd.models.entity.Product;

import java.text.SimpleDateFormat;

/**
 * @author yyd
 * @since 2022-10-04
 */
@RestController
@RequestMapping("/product")
@Api(tags = "商品首页接口")
@Slf4j
public class ProductController {
    @Autowired
    public ProductService iProductService;

    @ApiOperation("查商品")
    @GetMapping("get")
    @Doc
    public RestResponse likeProductNameList(@RequestParam("name") String name) {

        List<ProductVo> list = iProductService.likeProductNameList(name);
        return new RestResponse<>(list);
    }

    @ApiOperation("添加商品")
    @PostMapping("/add")
    @Doc
    public RestResponse addProduct(@RequestBody AddProductDto addProductDto) {

        int row = iProductService.addProduct(addProductDto);
        return new RestResponse<>(row);
    }
}
