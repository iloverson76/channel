package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreChain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreChainMapper extends BaseMapper<CcStoreChain> {

    List<CcStoreChain> findList(@Param("eo")  CcStoreChain eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}