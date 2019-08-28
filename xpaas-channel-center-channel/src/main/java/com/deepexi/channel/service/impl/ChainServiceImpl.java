package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.*;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.service.IChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChainServiceImpl implements IChainService {
    @Autowired
    ChainDAO iChainDAO;
    @Autowired
    ChainTypeDAO iChainTypeDAO;
    @Autowired
    BankAccountDAO iBankAccountDAO;
    @Autowired
    ChainBankDAO iChainBankDAO;

    @Override
    public ChainDTO getChain(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        ChainDO chainDO = iChainDAO.getById(id);
        if (null == chainDO) {
            return null;
        }
        ChainDTO chainDTO = chainDO.clone(ChainDTO.class, CloneDirection.OPPOSITE);
        //查询连锁所属分类, 获取分类信息
        ChainTypeDO chainTypeDO = iChainTypeDAO.getById(chainDTO.getChainTypeId());
        if(chainTypeDO != null){
            chainDTO.setChainTypeName(chainTypeDO.getChainTypeName());
        }

        //查询连锁的所有账户，获取账户信息
        //TODO


        return chainDO.clone(ChainDTO.class);
    }

    @Override
    @Transactional
    public Boolean insert(ChainDTO chainDTO) {
        ChainDO chainDO = chainDTO.clone(ChainDO.class);
        //新增连锁基本信息
        boolean result = iChainDAO.save(chainDO);
        //批量新增连锁账户信息
        List<BankAccountDTO> bankAccountVOS = chainDTO.getBankAccountList();
        List<BankAccountDO> bankAccountDOS = ObjectCloneUtils.convertList(bankAccountVOS, BankAccountDO.class);
        boolean insertBankAccountResult = iBankAccountDAO.saveBatch(bankAccountDOS);

        //批量新增账户、连锁关联信息
        List<ChainBankDO> chainBankDOS = new ArrayList<>();
        for(BankAccountDO bankAccount:bankAccountDOS){
            ChainBankDO chainBankDO = ChainBankDO.builder()
                    .bankAccountId(bankAccount.getId())
                    .chainId(chainDO.getId())
                    .build();
            chainBankDOS.add(chainBankDO);
        }
        boolean insertChainBankResult = iChainBankDAO.saveBatch(chainBankDOS);

        //连锁，账户，连锁账户关联都插入成功 返回true
        if(result && insertBankAccountResult && insertChainBankResult){
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(ChainDTO chainDTO) {
        if (chainDTO.getId() == null || chainDTO.getId() == 0L) {
            return false;
        }
        ChainDO chainDO = chainDTO.clone(ChainDO.class);
        boolean result = iChainDAO.updateById(chainDO);
        return result;
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return iChainDAO.removeByIds(ids);
    }
}
