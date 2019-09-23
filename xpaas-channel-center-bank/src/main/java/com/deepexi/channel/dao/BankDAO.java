package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.BankDO;
import com.deepexi.channel.domain.BankQuery;

import java.util.List;

public interface BankDAO extends IService<BankDO> {

    List<BankDO> findAll();

    List<BankDO> getBankByIds(List<Long> bankIds);

    List<BankDO> findList(BankQuery query);
}
