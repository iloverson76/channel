package com.deepexi.channel.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.AreaTypeDO;
import com.deepexi.channel.domain.AreaTypeQuery;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface AreaTypeMapper extends BaseMapper<AreaTypeDO> {

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode();

    List<AreaTypeDO> listNodeWithoutChildren();

    AreaTypeDO getChildNode(@Param("id") Long id);

    List<AreaTypeDO> listChildNodes (@Param("idPath") String idPath);

    List<AreaTypeDO> listAreaTypeByIds(@Param("ids") List<Long> areaTyeIdList);

    List<AreaTypeDO> listLinkedAreas(@Param("id") Long pk);

    List<String> listAreaTypeName();

    List<String> listAreaTypeNameEn();
}
