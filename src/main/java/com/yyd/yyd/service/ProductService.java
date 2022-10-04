package com.yyd.yyd.service;

import com.yyd.yyd.models.dto.AddProductDto;
import com.yyd.yyd.models.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyd.yyd.models.vo.ProductVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yyd
 * @since 2022-10-04
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据商品名称模糊查询
     * @param name 商品名称
     * @return List<ProductVo>
     */
    List<ProductVo> likeProductNameList(String name);

    /**
     * 添加商品
     * @param addProductDto
     * @return
     */
    int addProduct(AddProductDto addProductDto);

}
