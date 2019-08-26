package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeListDO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;

import java.util.List;

/**
 * <p>
 * 等级体系表 Mapper 接口
 * </p>
 *
 * @author chp
 * @since 2019-08-26
 */
public interface DistributorGradeMapper extends BaseMapper<DistributorGradeDO> {

   List<DistributorGradeListDO> listGradePage(DistributorGradeQuery query);
}
