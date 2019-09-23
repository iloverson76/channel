package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.BankDO;
import com.deepexi.channel.domain.BankQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@Mapper
public interface BankMapper extends BaseMapper<BankDO> {

    List<BankDO> findList(BankQuery query);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<BankDO> getBankByIds(@Param("ids") List<Long> ids);
}