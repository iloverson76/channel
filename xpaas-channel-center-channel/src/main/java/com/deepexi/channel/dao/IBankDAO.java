package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankVO;

import java.util.List;

public interface IBankDAO extends IService<BankDO> {

    List<BankDO> findAll();
}
