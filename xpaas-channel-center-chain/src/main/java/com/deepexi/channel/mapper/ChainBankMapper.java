package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.ChainBankDO;
import com.deepexi.channel.domain.ChainBankQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@Mapper
public interface ChainBankMapper extends BaseMapper<ChainBankDO> {

//    List<CcChainBank> findList(@Param("eo")  CcChainBank eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<ChainBankDO> getChainBankByChainId(@Param("id")Long id);

    boolean deleteByChainId(@Param("chainId") Long chainId);

    List<ChainBankDO> findList(ChainBankQuery query);
}