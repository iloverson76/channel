package com.deepexi.channel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
public interface AreaTypeMapper extends BaseMapper<AreaTypeDO> {

    List<AreaTypeDO> listAreaType(AreaTypeQuery query);

}
