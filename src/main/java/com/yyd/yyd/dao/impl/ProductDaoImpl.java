package com.yyd.yyd.dao.impl;

import com.yyd.yyd.dao.ProductDao;
import com.yyd.yyd.dao.mapper.ProductMapper;
import com.yyd.yyd.models.entity.Product;
import com.yyd.yyd.models.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductVo> likeProductNameList(String name) {
        return productMapper.likeProductNameList(name);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }
}
