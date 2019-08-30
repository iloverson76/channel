package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStore;
import com.deepexi.channel.domain.store.StoreDO;
import com.deepexi.channel.domain.store.StoreQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<StoreDO> {

    List<StoreDO> findList(StoreQuery query);

//    int deleteByIds(@Param("ids") List<Integer > ids);

}