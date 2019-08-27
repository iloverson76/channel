package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p>
 * 连锁类型表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface ChainTypeMapper extends BaseMapper<ChainTypeDO> {

    List<ChainTypeDO> listChainTypePage(ChainTypeQuery query);
}
