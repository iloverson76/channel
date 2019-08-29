package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.eo.CcBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BankMapper extends BaseMapper<BankDO> {

    List<BankDO> findList(BankDO eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}