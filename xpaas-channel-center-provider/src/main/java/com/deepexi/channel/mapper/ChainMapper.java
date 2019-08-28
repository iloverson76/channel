package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcChain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChainMapper extends BaseMapper<CcChain> {

    List<CcChain> findList(@Param("eo")  CcChain eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}