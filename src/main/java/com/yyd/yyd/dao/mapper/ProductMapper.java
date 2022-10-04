package com.yyd.yyd.dao.mapper;

import com.yyd.yyd.models.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyd.yyd.models.vo.ProductVo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yyd
 * @since 2022-10-04
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
    List<ProductVo> likeProductNameList(@Param("name") String name);
}
