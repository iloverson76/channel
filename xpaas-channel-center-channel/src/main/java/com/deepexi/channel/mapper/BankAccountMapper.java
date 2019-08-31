package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.eo.CcBankAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BankAccountMapper extends BaseMapper<BankAccountDO> {

    List<BankAccountDO> findList( BankAccountDO bankAccountDO);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<BankAccountDO> getBankAccountByIds(@Param("ids") List<Long> ids);
}