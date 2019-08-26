package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IChainDAO;
import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.chain.ChainTypeVO;
import com.deepexi.channel.service.IChainService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainServiceImpl implements IChainService {
    @Autowired
    IChainDAO iChainDAO;

    @Override
    public List<ChainTypeDTO> listChainType(ChainTypeQuery query) {
        return null;
    }
}
