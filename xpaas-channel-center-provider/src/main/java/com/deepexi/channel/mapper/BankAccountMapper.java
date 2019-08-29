package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcBankAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BankAccountMapper extends BaseMapper<CcBankAccount> {

    List<CcBankAccount> findList(@Param("eo")  CcBankAccount eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}