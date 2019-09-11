package com.deepexi.channel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import org.apache.ibatis.annotations.Param;

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

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode(@Param("tenantId") String tenantId, @Param("appId") String appId);

    List<AreaTypeDO> listNodeWithoutChildren(@Param("tenantId") String tenantId, @Param("appId") String appId);

    AreaTypeDO getChildNode(@Param("tenantId") String tenantId, @Param("appId") String appId, @Param("id") Long id);

    List<AreaTypeDO> listNotLimitedNode(@Param("tenantId") String tenantId, @Param("appId") String appId);

    List<AreaTypeDO> listChildNodes (@Param("tenantId") String tenantId, @Param("appId") String appId,@Param("idPath") String idPath);

    List<AreaTypeDO> listAreaTypeByIds(@Param("ids") List<Long> areaTyeIdList);

    List<AreaTypeDO> listLinkedAreas(@Param("id") long pk);
}
