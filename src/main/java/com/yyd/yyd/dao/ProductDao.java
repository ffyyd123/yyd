package com.yyd.yyd.dao;

import com.yyd.yyd.models.entity.Product;
import com.yyd.yyd.models.vo.ProductVo;

import java.util.List;


public interface ProductDao {

    List<ProductVo> likeProductNameList(String name);

    int addProduct(Product product);
}
