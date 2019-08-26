package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.service.IChainTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChainTypeServiceImpl implements IChainTypeService {
    @Autowired
    IChainTypeDAO iChainTypeDAO;
}
