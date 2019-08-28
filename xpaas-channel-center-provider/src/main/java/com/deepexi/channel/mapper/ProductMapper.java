package com.deepexi.channel.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.ProductDO;
import com.deepexi.channel.domain.ProductQuery;

/**
 * Created by donh on 2018/7/24.
 */
public interface ProductMapper extends BaseMapper<ProductDO> {

	/**
	 * 分页查询产品信息
	 * @param productQuery
	 * @return
	 */
    List<ProductDO> listProducts(ProductQuery productQuery);
}
