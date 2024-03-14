package com.yyd.yyd.service.impl;

import com.yyd.yyd.dao.ProductDao;
import com.yyd.yyd.models.dto.AddProductDto;
import com.yyd.yyd.models.entity.Product;
import com.yyd.yyd.dao.mapper.ProductMapper;
import com.yyd.yyd.models.vo.ProductVo;
import com.yyd.yyd.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyd.yyd.utils.JsonUtils;
import com.yyd.yyd.utils.StringUtils;
import com.yyd.yyd.utils.Utility;
import javafx.print.PageOrientation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yyd
 * @since 2022-10-04
 */
@Service
@Transactional
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    public ProductDao iProductDao;

    @Override
    public List<ProductVo> likeProductNameList(String name) {
        List<ProductVo> list = iProductDao.likeProductNameList(name);
        list.stream().forEach(
                vo -> {
                    if (!StringUtils.isEmpty(vo.getUrl())) {
                        List<String> list1 = JsonUtils.fromJson(vo.getUrl(), List.class);
                        if (!CollectionUtils.isEmpty(list1)) {
                            vo.setUrl(list1.get(0));
                        }
                    }
                }
        );
        return list;
    }


    @Override
    @Transactional
    public int addProduct(AddProductDto addProductDto) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductDto, product);
        product.setName(addProductDto.getName());
        product.setPrice(BigDecimal.valueOf(addProductDto.getPrice()));
        product.setRemark(addProductDto.getDescribe());
        product.setGid(Utility.simpleUUID());
//        product.setCreateTime(new java.sql.Date(new Date().getTime()));
        product.setCreator("566121EE70161C5CE05315110A0A8861");
//        product.setUpdateTime(new java.sql.Date(new Date().getTime()));
        product.setImgJson(JsonUtils.toJson(addProductDto.getImgUrls()));
        product.setStatus(0);
        return iProductDao.addProduct(product);
    }
}
