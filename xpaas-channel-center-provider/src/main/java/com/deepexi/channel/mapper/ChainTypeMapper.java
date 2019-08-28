package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcChainType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChainTypeMapper extends BaseMapper<CcChainType> {

    List<CcChainType> findList(@Param("eo")  CcChainType eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}