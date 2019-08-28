package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.*;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.extension.AppRuntimeEnv;
import com.deepexi.channel.service.ChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChainServiceImpl implements ChainService {
    private AppRuntimeEnv appRuntimeEnv = AppRuntimeEnv.getInstance();
    @Autowired
    IChainDAO iChainDAO;
//    @Autowired
//    IChainTypeDAO iChainTypeDAO;
//    @Autowired
//    IBankAccountDAO iBankAccountDAO;
//    @Autowired
//    IChainBankDAO iChainBankDAO;


    @Override
    public ChainDTO getChain(Long id) {
        ChainDO chainDO = iChainDAO.getById(id);
        if (null == chainDO) {
            return null;
        }
        return chainDO.clone(ChainDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @Transactional
    public Boolean insert(ChainDTO dto) {
        ChainDO chainDO = dto.clone(ChainDO.class);
        //新增连锁基本信息
        boolean result = iChainDAO.save(chainDO);

        return result;
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
