package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcChainBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcChainBankMapper extends BaseMapper<CcChainBank> {

    List<CcChainBank> findList(@Param("eo")  CcChainBank eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}