package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.service.*;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChainBusinessServiceImpl implements ChainBusinessService {

    @Autowired
    ChainService chainService;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    ChainBankService chainBankService;
    @Autowired
    ChainTypeService chainTypeService;

    @Override
    @Transactional
    public Boolean insertChain(ChainDTO dto) {
        //新增连锁基本信息
        boolean result = chainService.create(dto);
        //批量新增连锁账户信息
        List<BankAccountDTO> bankAccountDTOS = dto.getBankAccountList();
        if(CollectionUtil.isEmpty(bankAccountDTOS)){
            return result;
        }
        boolean insertBankAccountResult = bankAccountService.saveBatch(bankAccountDTOS);

        //批量新增账户、连锁关联信息
        List<ChainBankDTO> chainBankDTOS = new ArrayList<>();
        for(BankAccountDTO bankAccount:bankAccountDTOS){
            ChainBankDTO chainBankDO = ChainBankDTO.builder()
                    .bankAccountId(bankAccount.getId())
                    .chainId(dto.getId())
                    .build();
            chainBankDO.setVersion(dto.getVersion());

            chainBankDTOS.add(chainBankDO);
        }
        boolean insertChainBankResult = chainBankService.saveBatch(chainBankDTOS);

        //连锁，账户，连锁账户关联都插入成功 返回true
        if(result && insertBankAccountResult && insertChainBankResult){
            return true;
        }
        return false;
    }

    @Override
    public ChainDTO getChain(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        //查询分类
        ChainDTO dto = chainService.detail(id);
        if(dto == null){
            return null;
        }
        //查询连锁所属分类, 获取分类信息
        ChainTypeDTO chainTypeDTO = chainTypeService.detail(dto.getChainTypeId());
        if(chainTypeDTO != null){
            dto.setChainTypeName(chainTypeDTO.getChainTypeName());
        }

        //查询连锁的所有账户，获取账户信息
        //TODO


        return dto;
    }

    @Override
    public Boolean deleteChain(List<Long> ids) {
        //TODO 判断门店删除是否合法,是否具有子节点

        //删除合法
        return chainService.delete(ids);
    }

    @Override
    @Transactional
    public Boolean updateChain(ChainDTO dto) {
        //TODO 更新银行账户信息

        //更新连锁店基本信息
        return chainService.update(dto);
    }
}
