package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.BankAccountDO;
import com.deepexi.channel.domain.BankAccountQuery;

import java.util.List;

public interface BankAccountDAO extends IService<BankAccountDO> {

    List<BankAccountDO> getBankAccountByIds(List<Long> bankAccountIds);

    int deleteBatchIds(List<Long> ids);

    List<BankAccountDO> findList(BankAccountQuery query);
}
