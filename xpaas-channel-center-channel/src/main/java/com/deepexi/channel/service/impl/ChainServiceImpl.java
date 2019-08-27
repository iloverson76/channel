package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IChainDAO;
import com.deepexi.channel.domain.chain.*;
import com.deepexi.channel.service.IChainService;
import com.deepexi.util.CollectionUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainServiceImpl implements IChainService {
    @Autowired
    IChainDAO iChainDAO;

    @Override
    public ChainDTO getChain(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        ChainDO chainDO = iChainDAO.getById(id);
        if (null == chainDO) {
            return null;
        }
        return chainDO.clone(ChainDTO.class);
    }

    @Override
    public Boolean insert(ChainDTO chainDTO) {
        ChainDO chainDO = chainDTO.clone(ChainDO.class);
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
