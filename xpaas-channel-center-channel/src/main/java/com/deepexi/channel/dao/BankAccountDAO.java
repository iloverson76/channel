package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.bank.BankAccountDO;

import java.util.List;

public interface BankAccountDAO extends IService<BankAccountDO> {

    List<BankAccountDO> getBankAccountByIds(List<Long> bankAccountIds);

    int deleteBatchIds(List<Long> ids);
}
