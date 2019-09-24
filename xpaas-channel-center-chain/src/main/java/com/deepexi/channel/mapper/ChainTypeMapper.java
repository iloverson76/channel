package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.ChainTypeDO;
import com.deepexi.channel.domain.ChainTypeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@Mapper
public interface ChainTypeMapper extends BaseMapper<ChainTypeDO> {

    List<ChainTypeDO> findList(ChainTypeQuery query);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<ChainTypeDO> findParentList(@Param("ids")List<Long> ids);

    List<ChainTypeDO> listNotLimitedNode(@Param("tenantId") String tenantId, @Param("appId") String appId);

    List<ChainTypeDO> listChildNodes (@Param("tenantId") String tenantId, @Param("appId") String appId,@Param("idPath") String idPath);
}